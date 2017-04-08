package controllers;

import java.util.Date;
import java.util.List;

import models.*;
import play.mvc.*;
import viewmodels.NarudzbenicaViewModel;

public class OtpremnicaController extends Controller {

    public static void generisiOtpremnicu(long fakturaId) {
    	Faktura faktura = Faktura.findById(fakturaId);
    	Otpremnica otpremnica = new Otpremnica();
    	otpremnica.datumOtpremnice = new Date();
    	otpremnica.kupac = faktura.poslovniPartneri;
    	BusinessYear currentYear = BusinessYearController.trenutnaGodina();
    	otpremnica.poslovnaGodina = currentYear;
    	otpremnica.brojOtpremnice = otpremnica.poslovnaGodina.poslednjiBrOtpremnice+1;
    	otpremnica.save();
    	List<StavkaFakture> stavke = StavkaFakture.find("byFaktura_idAndStornirano", faktura.id, false).fetch();
    	for(StavkaFakture stavka : stavke) {
    		StavkaOtpremnice oStavka = new StavkaOtpremnice();
    		oStavka.iznosStavke = stavka.iznosStavke;
    		oStavka.kolicina = stavka.kolicina;
    		oStavka.napomena = "";
    		oStavka.roba = stavka.roba;
    		oStavka.otpremnica = otpremnica;
    		oStavka.save();
    	}
    	currentYear.poslednjiBrOtpremnice+=1;
		currentYear.save();
		FakturaController.showAll();
    }
    
    public static void showAll(){
		List<Otpremnica> otpremnice = Otpremnica.find("order by id desc").fetch();
		NarudzbenicaViewModel narudzbeniceViewModel = NarudzbenicaController.narudzbenice();
		renderTemplate("Dobavljac/otpremnice.html", otpremnice, narudzbeniceViewModel);
	}
    
    public static void prikaziDetaljno(long id){
    	Otpremnica otpremnica = Otpremnica.findById(id);
		Company company = Company.findById(1L);
		NarudzbenicaViewModel narudzbeniceViewModel = NarudzbenicaController.narudzbenice();
		renderTemplate("otpremnice/otpremnice-detaljno.html", otpremnica, company, narudzbeniceViewModel);
	}
    
    public static void dodajNapomenu(String id, String napomena) {
    	StavkaOtpremnice stavka = StavkaOtpremnice.findById(Long.parseLong(id));
    	stavka.napomena = napomena;
    	stavka.save();
    	prikaziDetaljno(stavka.otpremnica.id);
    }

}
