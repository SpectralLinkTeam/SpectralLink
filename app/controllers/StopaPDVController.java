package controllers;

import java.util.Date;
import java.util.List;

import models.StopaPDV;
import play.mvc.Controller;

public class StopaPDVController extends Controller {
// crud i search
	
	// samo aktuelna moze da se menja - ostale read only
	
	public static void show(){
	    Dobavljac.roba();
    }
	
	public static void add(StopaPDV stopa){
		stopa.save();
	    show();
    }
	
	public static void edit(int stopaVisoki, int stopaNiski){
		StopaPDV visokiPDV = StopaPDV.findById(1L);
		StopaPDV niskiPDV = StopaPDV.findById(2L);

		visokiPDV.procenat = stopaVisoki;
		niskiPDV.procenat = stopaNiski;
		/*if (stopa.datumVazenja.after(danasnjiDatum)) {
			stopa.save();
		}*/
		visokiPDV.save();
		niskiPDV.save();
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
