package controllers;
import models.*;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alligator on 25.12.16..
 */
public class Dobavljac extends Controller{

    // redirekcija na webshop kao default stranica Dobavljaca
    public static void redirect(){
        // ovde cemo povuci iz baze dobavljaca tipa Company
        Company company = new Company();
        renderTemplate("Dobavljac/webshop.html", company);
    }

    // redirekcije sa dummy podacima ( prazne liste )
    public static void webshop(){
    	//List<Roba> roba = Roba.find("byIsDeleted", 0).fetch();
    	//Cenovnik cenovnik = Cenovnik.find("order by datumVazenja desc").first();
    	//List<StavkaCenovnika> cene = StavkaCenovnika.find("byCenovnik", cenovnik.id).fetch();
    	List<StavkaFakture> proizvodi = new ArrayList<>();
		renderTemplate("Dobavljac/webshop.html", proizvodi);
	}

	public static void cenovnik(){
        Cenovnik cenovnik = new Cenovnik();
        renderTemplate("Dobavljac/cenovnik.html", cenovnik);
    }

    public static void poslovniPartneri(){
	    List<BusinessPartner> pp = new ArrayList<>();
        renderTemplate("Dobavljac/poslovni-partneri.html", pp);
    }

    public static void narudzbenice(){
        // napraviti Narudzbenica u modelu
        List<Object> narudzbenice = new ArrayList<>();
        renderTemplate("Dobavljac/narudzbenice.html", narudzbenice);
    }
    
}
