package controllers;

import java.util.Date;
import java.util.List;

import dto.*;
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
			fStavka = popuniStavku(stavka.roba, stavka.kolicina, 0, faktura, fStavka);
			fStavka.save();
		}
		
		faktura = izracunajIznose(faktura);
		faktura.save();
		currentYear.poslednjiBrFakture+=1;
		currentYear.save();
		narudzbenica.fakturaGenerisana=true;
		narudzbenica.save();
	}
	
	private static Faktura izracunajIznose(Faktura faktura) {
		List<StavkaFakture> stavke = StavkaFakture.find("byStorniranoAndFaktura_id", false, faktura.id).fetch();
		double osnovica = 0;
		double pdv = 0;
		
		for (StavkaFakture stavka : stavke) {
			osnovica += stavka.osnovicaPDV;
			pdv += stavka.iznosPDV;
		}
		faktura.osnovica = osnovica;
		faktura.ukupanPDV = pdv;
		faktura.iznosZaPlacanje = osnovica+pdv;
		return faktura;
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
		StavkaFakture stavka = StavkaFakture.findById(stavkaId);
		stavka.stornirano = true;
		stavka.save();
		Faktura faktura = izracunajIznose(stavka.faktura);
		faktura.save();
		prikaziDetaljno(faktura.id);
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
		StavkaFakture stavka = new StavkaFakture();
		stavka = popuniStavku(roba, kolicina, rabat, faktura, stavka);
		stavka.save();
		faktura = izracunajIznose(faktura);
		faktura.save();
		prikaziDetaljno(faktura.id);
	}
	
	private static StavkaFakture popuniStavku(Roba roba, int kolicina, int rabat, Faktura faktura, StavkaFakture retStavka) {
		Cenovnik cenovnik = Cenovnik.find("order by datumVazenja desc").first();
//		StavkaFakture retStavka = new StavkaFakture();
		retStavka.roba = roba;
		retStavka.kolicina = kolicina;
		retStavka.rabat = rabat;
		retStavka.faktura = faktura;
		retStavka.cenovnik = cenovnik;
		
		retStavka.jedinicnaCena = izracunajJedinicnuCenu(roba, cenovnik);
		double osnovica = retStavka.jedinicnaCena*kolicina;
		retStavka.osnovicaPDV = Math.round(osnovica*(1-(double)rabat/100));
		int procenatPDV = izracunajPdvProcenat(roba, cenovnik);
		retStavka.procenatPDV = procenatPDV;
		retStavka.iznosPDV = Math.round(retStavka.osnovicaPDV * ((double)procenatPDV/100));
		retStavka.iznosStavke=retStavka.osnovicaPDV+retStavka.iznosPDV;
		
		return retStavka;
	}
	
	public static void editStavka(long id, String proizvod, int kolicina, int rabat) {
		StavkaFakture stavka = StavkaFakture.findById(id);
		Roba roba = Roba.findById(Long.parseLong(proizvod));
		Faktura faktura = izracunajIznose(stavka.faktura);
		stavka.roba = roba;
		stavka.kolicina = kolicina;
		stavka.rabat = rabat;
		stavka = popuniStavku(roba, kolicina, rabat, faktura, stavka);
		stavka.save();
		faktura.save();
		prikaziDetaljno(faktura.id);
	}
	
	
	public static void searchJsonById(long id){
		Faktura faktura = Faktura.findById(id);
		FakturaDTO forNetwork = new FakturaDTO(faktura);
		renderJSON(forNetwork);
	}
	
	public static void searchJsonByStavkaId(long id){
		StavkaFakture stavka = StavkaFakture.findById(id);
		StavkaFaktureDTO forNetwork = new StavkaFaktureDTO(stavka);
		renderJSON(forNetwork);
	}
}
