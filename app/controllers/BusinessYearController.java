package controllers;

import java.util.List;

import dto.BusinessYearDTO;
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
		complete(poslovnaGodina);
		BusinessYear novaPoslovnaGodina = new BusinessYear();
		novaPoslovnaGodina.year = poslovnaGodina.year++;
		novaPoslovnaGodina.save();
//		poslovnaGodina.save();
		//pronaci poslednju aktivnu
		//na njoj pozvati complete
	    show();
    }
	
	public static void edit(BusinessYear poslovnaGodina){
		poslovnaGodina.save();
		show();
	}
	
	private static void complete(BusinessYear poslovnaGodina){
//		BusinessYear poslovnaGodina = BusinessYear.findById(id);
		poslovnaGodina.completed = true;
		poslovnaGodina.save();
	}
	
	public static void search(String yearInput){
		List<BusinessYear> pp = BusinessYear.find("byYear", yearInput).fetch();
        renderTemplate("Dobavljac/BusinessYear/show.html", pp);
	}
	
	public static BusinessYear trenutnaGodina(){
		return BusinessYear.find("completed=? order by id desc", false).first();
	}

	public static void searchJsonYear(){
		BusinessYear by = BusinessYear.find("completed=? order by id desc", false).first();
		renderJSON(new BusinessYearDTO(by));
	}
}
