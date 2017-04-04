package controllers;

import java.util.Date;
import java.util.List;

import models.*;
import play.mvc.Controller;
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
		BusinessYear currentYear = BusinessYear.find("byCompletedAndOrderByIdDesc", false).first();
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
		StopaPDV pdvStopa = StopaPDV.find("byPdv_idAndOrderByDatumVazenjaDesc", pdv.id).first();
		return pdvStopa.procenat;
	}
	// ..... fakture koje su zakljucene se ne mogu menjati - READONLY
	
	public static void showAll(){
		List<Faktura> fakture = Faktura.find("byIsDeleted", 0).fetch();
		renderTemplate("Dobavljac/fakture.html", fakture);
	}
	
	public static void generisiSve(){
		List<Narudzbenica> narudzbenice = Narudzbenica.find("byFakturaGenerisanaAndIsDeleted", false, 0).fetch();
		for (Narudzbenica n : narudzbenice){
			generisanjeFakture(n);
		}
		showAll();
	}
}
