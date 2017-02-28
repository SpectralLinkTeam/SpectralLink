package controllers;
import models.Cenovnik;
import models.Company;
import models.Roba;
import models.StavkaCenovnika;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alligator on 25.12.16..
 */
public class Dobavljac extends Controller{

    public static void redirect(){

        // ovde cemo povuci iz baze dobavljaca tipa Company
        Company company = new Company();
        renderTemplate("Dobavljac/dobavljac.html", company);
    }
    
    public static void webshop(){
    	//List<Roba> roba = Roba.find("byIsDeleted", 0).fetch();
    	//Cenovnik cenovnik = Cenovnik.find("order by datumVazenja desc").first();
    	//List<StavkaCenovnika> cene = StavkaCenovnika.find("byCenovnik", cenovnik.id).fetch();
    	
		render();
	}
    
    
}
