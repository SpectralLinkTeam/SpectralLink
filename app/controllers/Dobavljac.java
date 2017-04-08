package controllers;
import models.*;
import play.db.jpa.JPA;
import play.mvc.Controller;
import viewmodels.NarudzbenicaViewModel;


import java.util.ArrayList;
import java.util.List;

import javax.management.Query;

// redirekcioni kontroler
public class Dobavljac extends Controller{

    public static void redirect(){
        // ovde cemo povuci iz baze dobavljaca tipa Company
        //Company company = new Company();
        //renderTemplate("Dobavljac/roba.html", company);
        roba();
    }

    // WEBSHOP redirection
    public static void webshop() {
        List<Roba> roba = Roba.find("byIsDeleted", 0).fetch();
        renderTemplate("Dobavljac/webshop.html", roba);
    }

    public static void addToCart(long id){

    }

    //ADMIN - navigation

    public static void roba(){
    	List<Roba> roba = Roba.find("byIsDeleted", 0).fetch();
    	//Cenovnik cenovnik = Cenovnik.find("order by datumVazenja desc").first();
    	//List<StavkaCenovnika> cene = StavkaCenovnika.find("byCenovnik", cenovnik.id).fetch();
    	NarudzbenicaViewModel narudzbeniceViewModel = NarudzbenicaController.narudzbenice();
		renderTemplate("Dobavljac/roba.html", roba, narudzbeniceViewModel);
	}

	public static void cenovnik(){
        Cenovnik cenovnik = Cenovnik.find("order by datumVazenja asc").first();
        NarudzbenicaViewModel narudzbeniceViewModel = NarudzbenicaController.narudzbenice();
        renderTemplate("Dobavljac/cenovnik.html", cenovnik, narudzbeniceViewModel);
    }

    public static void poslovniPartneri(){
	    List<BusinessPartner> pp = BusinessPartner.find("byIsDeleted", 0).fetch(); //.findAll();
        NarudzbenicaViewModel narudzbeniceViewModel = NarudzbenicaController.narudzbenice();

        renderTemplate("Dobavljac/poslovni-partneri.html", pp, narudzbeniceViewModel);
    }

    public static void grupeRobe(){
        List<GrupaRobe> grupeRobe = GrupaRobe.find("byIsDeleted", 0).fetch(); //.findAll();
        NarudzbenicaViewModel narudzbeniceViewModel = NarudzbenicaController.narudzbenice();
        renderTemplate("Dobavljac/grupe-robe.html", grupeRobe, narudzbeniceViewModel);
    }

    public static void narudzbenice(){
        // napraviti Narudzbenica u modelu
        List<Object> narudzbenice = new ArrayList<>();
        NarudzbenicaViewModel narudzbeniceViewModel = NarudzbenicaController.narudzbenice();

        renderTemplate("Dobavljac/narudzbenice.html", narudzbenice, narudzbeniceViewModel);
    }

    public static void fakture(){
        List<Faktura> fakture = Faktura.find("byIsDeleted", 0).fetch(); //.findAll();
        NarudzbenicaViewModel narudzbeniceViewModel = NarudzbenicaController.narudzbenice();
        renderTemplate("Dobavljac/fakture.html", fakture, narudzbeniceViewModel);

    }

    public static void magacin(){
        List<Roba> roba = Roba.findAll();
        renderTemplate("Dobavljac/magacin.html", roba);
    }

    public static void izvestaji(){
        NarudzbenicaViewModel narudzbeniceViewModel = NarudzbenicaController.narudzbenice();
        renderTemplate("Dobavljac/izvestaji.html", narudzbeniceViewModel);
    }

}
