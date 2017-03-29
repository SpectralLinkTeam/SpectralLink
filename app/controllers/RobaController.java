package controllers;

import java.util.List;

import models.Roba;
import play.mvc.Controller;

public class RobaController extends Controller {
// crud i search
	
	public static void show(){
	    List<Roba> lista = Roba.find("byIsDeleted", "0").fetch();
        renderTemplate("Dobavljac/Roba/show.html", lista);
    }
	
	public static void add(Roba roba){
		roba.save();
	    show();
    }
	
	public static void edit(Roba roba){
		roba.save();
		show();
	}
	
	public static void delete(long id){
	    //logicko brisanje
		Roba roba = Roba.findById(id);
		roba.IsDeleted = true;
		roba.save();
		show();
	}
	
	public static void search(String searchTerm){
		List<Roba> lista = Roba.find("byIsDeletedAndNazivLikeAndOpisLike", "0", "%"+searchTerm+"%", "%"+searchTerm+"%").fetch();
        renderTemplate("Dobavljac/Roba/show.html", lista);
	}
}
