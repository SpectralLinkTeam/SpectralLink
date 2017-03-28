package controllers;

import java.util.List;

import models.Mesto;
import play.mvc.Controller;

public class MestoController extends Controller {
	
// crud i search
	
	public static void show(){
	    List<Mesto> pp = Mesto.findAll();
        renderTemplate("Dobavljac/Mesto/show.html", pp);
    }
	
	public static void add(Mesto mesto){
		mesto.save();
	    show();
    }
	
	public static void edit(Mesto mesto){
		mesto.save();
		show();
	}
	//-- fizicko brisanje
	public static void delete(long id){
		Mesto mesto = Mesto.findById(id);
		mesto.delete();
		show();
	}
	
	public static void search(String searchTerm){
		
	}
}
