package controllers;

import java.util.List;

import models.BusinessPartner;
import play.mvc.Controller;

public class BusinessPartnerController extends Controller {

	public static void show(){
	    List<BusinessPartner> pp = BusinessPartner.findAll();
        renderTemplate("Dobavljac/BusinessPartner/show.html", pp);
    }
	
	public static void add(BusinessPartner partner){
	    
    }
	
	public static void edit(BusinessPartner partner){
		    
	}
	
	public static void delete(long id){
	    //logicko brisanje
	}
	
	public static void search(String searchTerm){
		
	}
	
}
