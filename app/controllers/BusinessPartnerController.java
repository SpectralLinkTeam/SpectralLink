package controllers;

import java.util.List;

import models.BusinessPartner;
import play.mvc.Controller;

public class BusinessPartnerController extends Controller {

	public static void show(){
	    List<BusinessPartner> pp = BusinessPartner.find("byIsDeleted", "0").fetch();
        renderTemplate("Dobavljac/BusinessPartner/show.html", pp);
    }
	
	public static void add(BusinessPartner partner){
		partner.save();
	    show();
    }
	
	public static void edit(BusinessPartner partner){
		partner.save();
		show();
	}
	
	public static void delete(long id){
	    //logicko brisanje
		BusinessPartner kupac = BusinessPartner.findById(id);
		kupac.IsDeleted = true;
		kupac.save();
		show();
	}
	
	public static void search(String searchTerm){
		List<BusinessPartner> pp = BusinessPartner.find("byIsDeletedAndNameLike", "0", "%"+searchTerm+"%").fetch();
        renderTemplate("Dobavljac/BusinessPartner/show.html", pp);
	}
	
}
