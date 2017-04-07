package controllers;

import java.util.Date;
import java.util.List;

import dto.FakturaDTO;
import dto.RobaDTO;
import models.*;
import play.mvc.Controller;
import play.mvc.results.RenderTemplate;
import viewmodels.NarudzbenicaViewModel;

public class FakturaController extends Controller {
//crud i search
	// generisanje fakture na osnovu narudzbenice
	
	public static void generisiFakturu(long id){
		Narudzbenica narudzbenica = Narudzbenica.findById(id);
		generisanjeFakture(narudzbenica);
		showAll();
	}
	
	private static void generisanjeFakture(Narudzbenica narudzbenica){
		Cenovnik cenovnik = Cenovnik.find("order by datumVazenja desc").first();
		Faktura faktura = new Faktura();
		faktura.datumFakture = new Date();
		double osnovica=0;
		double pdv=0;
		faktura.poslovniPartneri = narudzbenica.kupac;
		BusinessYear currentYear = BusinessYearController.trenutnaGodina();
		faktura.poslovnaGodina = currentYear;
		faktura.brojFakture = faktura.poslovnaGodina.poslednjiBrFakture+1;
		faktura.save();
		for(StavkaNarudzbenice stavka : narudzbenica.stavkeNarudzbenice) {
			StavkaFakture fStavka = new StavkaFakture();
			fStavka.cenovnik = cenovnik;
			fStavka.faktura=faktura;
			fStavka.jedinicnaCena = izracunajJedinicnuCenu(stavka.roba, cenovnik);
			fStavka.rabat=0;
			fStavka.kolicina=stavka.kolicina;
			fStavka.procenatPDV = izracunajPdvProcenat(stavka.roba, cenovnik);
			fStavka.iznosPDV=(fStavka.jedinicnaCena*stavka.kolicina*fStavka.procenatPDV)/100;
			pdv += fStavka.iznosPDV;
			osnovica+=(fStavka.jedinicnaCena*fStavka.kolicina);
			fStavka.roba = stavka.roba;
			fStavka.setIznosStavke();
			//faktura.stavkeFakture.add(fStavka);
			fStavka.save();
		}
		faktura.osnovica = osnovica;
		faktura.ukupanPDV=pdv;
		faktura.iznosZaPlacanje=osnovica+pdv;
		faktura.save();
		currentYear.poslednjiBrFakture+=1;
		currentYear.save();
		narudzbenica.fakturaGenerisana=true;
		narudzbenica.save();
	}
	
	private static double izracunajJedinicnuCenu(Roba roba, Cenovnik cenovnik) {
		StavkaCenovnika cena = StavkaCenovnika.find("byRoba_idAndCenovnik_id", roba.id, cenovnik.id).first();
		return cena.cena;
}

	public static int izracunajPdvProcenat(Roba roba, Cenovnik cenovnik) {
		PDV pdv = roba.grupaRobe.pdv;
		StopaPDV pdvStopa = StopaPDV.find("Pdv_id=? order by datumVazenja desc", pdv.id).first();
		return pdvStopa.procenat;
	}
	// ..... fakture koje su zakljucene se ne mogu menjati - READONLY
	
	public static void showAll(){
		List<Faktura> fakture = Faktura.find("isDeleted=? order by id desc", 0).fetch();
		NarudzbenicaViewModel narudzbeniceViewModel = NarudzbenicaController.narudzbenice();
		renderTemplate("Dobavljac/fakture.html", fakture, narudzbeniceViewModel);
	}
	
	public static void generisiSve(){
		List<Narudzbenica> narudzbenice = Narudzbenica.find("byFakturaGenerisanaAndIsDeleted", false, 0).fetch();
		for (Narudzbenica n : narudzbenice){
			generisanjeFakture(n);
		}
		showAll();
	}

	
	public static void novaFaktura(long kupac){
		BusinessPartner partner = BusinessPartner.findById(kupac);
		BusinessYear poslovnaGodina = BusinessYearController.trenutnaGodina();
		Company company = Company.findById(1L);
		Faktura faktura = new Faktura();
		faktura.brojFakture = poslovnaGodina.poslednjiBrFakture+1;
		faktura.datumFakture = new Date();
		faktura.poslovnaGodina = poslovnaGodina;
		faktura.poslovniPartneri = partner;
		faktura.save();
		poslovnaGodina.poslednjiBrFakture+=1;
		poslovnaGodina.save();
		String slovima = Faktura.Slovima(faktura.iznosZaPlacanje);
		NarudzbenicaViewModel narudzbeniceViewModel = NarudzbenicaController.narudzbenice();
		renderTemplate("fakture/fakture-detaljno.html", faktura, company, narudzbeniceViewModel, slovima);
	}

	public static void prikaziDetaljno(long id){
		Faktura faktura = Faktura.findById(id);
		Company company = Company.findById(1L);
		NarudzbenicaViewModel narudzbeniceViewModel = NarudzbenicaController.narudzbenice();
		String slovima = Faktura.Slovima(faktura.iznosZaPlacanje);
		renderTemplate("fakture/fakture-detaljno.html", faktura, company, narudzbeniceViewModel, slovima);
	}

	public static void delete(long id){
		Faktura faktura = Faktura.findById(id);
		faktura.IsDeleted=true;
		faktura.save();
		showAll();
	}
	
	public static void storniraj(long stavkaId){
	}
	
	public static void edit(long id, String kupac){
		Faktura faktura = Faktura.findById(id);
		faktura.poslovniPartneri = BusinessPartner.findById(Long.parseLong(kupac));
		faktura.save();
		showAll();
	}
	
	public static void dodajStavku(String proizvod, int kolicina, int rabat, String fakturaId){
		Faktura faktura = Faktura.findById(Long.parseLong(fakturaId));
		Roba roba = Roba.findById(Long.parseLong(proizvod));
		StavkaFakture stavka = popuniStavku(roba, kolicina, rabat, faktura);
	}
	
	private static StavkaFakture popuniStavku(Roba roba, int kolicina, int rabat, Faktura faktura) {
		Cenovnik cenovnik = Cenovnik.find("order by datumVazenja desc").first();
		StavkaFakture retStavka = new StavkaFakture();
		retStavka.roba = roba;
		retStavka.kolicina = kolicina;
		retStavka.rabat = rabat;
		retStavka.faktura = faktura;
		
		retStavka.jedinicnaCena = izracunajJedinicnuCenu(roba, cenovnik);
		double osnovica = retStavka.jedinicnaCena*kolicina;
		
		return retStavka;
	}
	
	
	public static void searchJsonById(long id){
		Faktura faktura = Faktura.findById(id);
		FakturaDTO forNetwork = new FakturaDTO(faktura);
		renderJSON(forNetwork);
	}
}
