package controllers;
import models.*;
import play.mvc.Controller;
import viewmodels.NarudzbenicaViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alligator on 25.12.16..
 */
public class Dobavljac extends Controller{

    // redirekcija na webshop kao default stranica Dobavljaca
    public static void redirect(){
        // ovde cemo povuci iz baze dobavljaca tipa Company
        //Company company = new Company();
        //renderTemplate("Dobavljac/roba.html", company);
        roba();
    }

    //WEBSHOP
    public static void webshop() {
        List<Roba> roba = Roba.find("byIsDeleted", 0).fetch();
        
        renderTemplate("Dobavljac/webshop.html", roba);
    }

    public static void addToCart(long id){

    }

    //ADMIN

    // redirekcije sa dummy podacima ( prazne liste )
    public static void roba(){
    	List<Roba> roba = Roba.find("byIsDeleted", 0).fetch();
    	//Cenovnik cenovnik = Cenovnik.find("order by datumVazenja desc").first();
    	//List<StavkaCenovnika> cene = StavkaCenovnika.find("byCenovnik", cenovnik.id).fetch();
    	NarudzbenicaViewModel narudzbenice = NarudzbenicaController.narudzbenice();
		renderTemplate("Dobavljac/roba.html", roba, narudzbenice);
	}

	public static void cenovnik(){
        Cenovnik cenovnik = Cenovnik.find("order by datumVazenja desc").first();
        renderTemplate("Dobavljac/cenovnik.html", cenovnik);
    }

    public static void poslovniPartneri(){
	    List<BusinessPartner> pp = BusinessPartner.findAll();
        renderTemplate("Dobavljac/poslovni-partneri.html", pp);
    }

    public static void narudzbenice(){
        // napraviti Narudzbenica u modelu
        List<Object> narudzbenice = new ArrayList<>();
        renderTemplate("Dobavljac/narudzbenice.html", narudzbenice);
    }



    // CREATE or EDIT entities

    public static void create(String grupaRobe, String naziv, double cena, int raspKol, String opis, String jedinicaMere){
        Roba roba = new Roba();
        roba.grupaRobe = GrupaRobe.findById(Long.parseLong(grupaRobe));
        roba.naziv = naziv;
        roba.raspKol = raspKol;
        roba.opis = opis;
        roba.jedinicaMere = jedinicaMere;
        roba.save();

        StavkaCenovnika sc = new StavkaCenovnika();
        sc.cena = cena;
        sc.cenovnik = Cenovnik.findById(1L);
        sc.roba = roba;
        sc.save();
        roba();
    }

    public static void addToCart(long id, String kolicina){
        System.out.println(kolicina + String.valueOf(id));
    }
    
}
