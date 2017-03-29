package controllers;

import java.util.List;

import models.BusinessPartner;
import models.BusinessYear;
import play.mvc.Controller;

public class BusinessYearController extends Controller {

	//crud i search
	
	public static void show(){
	    List<BusinessYear> pp = BusinessYear.findAll();
        renderTemplate("Dobavljac/BusinessYear/show.html", pp);
    }
	
	public static void add(BusinessYear poslovnaGodina){
		poslovnaGodina.save();
	    show();
    }
	
	public static void edit(BusinessYear poslovnaGodina){
		poslovnaGodina.save();
		show();
	}
	
	public static void complete(long id){
		BusinessYear poslovnaGodina = BusinessYear.findById(id);
		poslovnaGodina.completed = true;
		poslovnaGodina.save();
		show();
	}
	
	public static void search(String yearInput){
		List<BusinessPartner> pp = BusinessPartner.find("byYear", yearInput).fetch();
        renderTemplate("Dobavljac/BusinessYear/show.html", pp);
	}
}
