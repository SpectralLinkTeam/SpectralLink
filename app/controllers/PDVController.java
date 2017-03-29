package controllers;

import java.util.List;

import models.PDV;
import play.mvc.Controller;

public class PDVController extends Controller {
// crud i search
	public static void show(){
	    List<PDV> lista = PDV.findAll();
        renderTemplate("Dobavljac/PDV/show.html", lista);
    }
	
	public static void add(PDV pdv){
		pdv.save();
	    show();
    }
	
	public static void edit(PDV pdv){
		pdv.save();
		show();
	}
	//-- fizicko brisanje
	public static void delete(long id){
		PDV pdv = PDV.findById(id);
		pdv.delete();
		show();
	}
	
	public static void search(String searchTerm){
		List<PDV> lista = PDV.find("byNaziv", "%" + searchTerm + "%").fetch();
        renderTemplate("Dobavljac/PDV/show.html", lista);
	}
}
