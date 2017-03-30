package controllers;

import java.util.Date;
import java.util.List;

import models.StopaPDV;
import play.mvc.Controller;

public class StopaPDVController extends Controller {
// crud i search
	
	// samo aktuelna moze da se menja - ostale read only
	
	public static void show(){
	    List<StopaPDV> lista = StopaPDV.findAll();
        renderTemplate("Dobavljac/StopaPDV/show.html", lista);
    }
	
	public static void add(StopaPDV stopa){
		stopa.save();
	    show();
    }
	
	public static void edit(StopaPDV stopa){
		Date danasnjiDatum = new Date();
		if (stopa.datumVazenja.after(danasnjiDatum)) {
			stopa.save();
		}
		show();
	}
	//-- fizicko brisanje
	public static void delete(long id){
		StopaPDV stopa = StopaPDV.findById(id);
		stopa.delete();
		show();
	}
	
	public static void search(String searchTerm){
		List<StopaPDV> lista = StopaPDV.find("byNaziv", "%" + searchTerm + "%").fetch();
        renderTemplate("Dobavljac/StopaPDV/show.html", lista);
	}
}
