package controllers;

import java.util.List;

import models.GrupaRobe;
import play.mvc.Controller;

public class GrupaRobeController extends Controller {
// crud i search
	
	public static void show(){
	    List<GrupaRobe> lista = GrupaRobe.find("byIsDeleted", "0").fetch();
        renderTemplate("Dobavljac/GrupaRobe/show.html", lista);
    }
	
	public static void add(GrupaRobe gr){
		gr.save();
	    show();
    }
	
	public static void edit(GrupaRobe gr){
		gr.save();
		show();
	}
	
	public static void delete(long id){
	    //logicko brisanje
		GrupaRobe gr = GrupaRobe.findById(id);
		gr.IsDeleted = true;
		gr.save();
		show();
	}
	
	public static void search(String searchTerm){
		List<GrupaRobe> lista = GrupaRobe.find("byIsDeletedAndNazivLike", "0", "%"+searchTerm+"%").fetch();
        renderTemplate("Dobavljac/GrupaRobe/show.html", lista);
	}
}
