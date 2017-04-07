package controllers;

import java.util.Date;
import java.util.List;

import models.Cenovnik;
import models.Roba;
import models.StavkaCenovnika;
import play.mvc.Controller;

public class CenovnikController extends Controller {
	
//crud i search
	
	//napomena editovanje dozvoljeno samo aktuelnom cenovniku / ostale readonly
	
	public static void show(){
	    List<Cenovnik> cenovnici = Cenovnik.find("byIsDeleted",0).fetch();
        renderTemplate("Dobavljac/Cenovnik/show.html", cenovnici);
    }
	
	public static void add(Cenovnik cenovnik){
		cenovnik.save();
	    show();
    }
	
	public static void edit(Cenovnik cenovnik){
		//napomena editovanje dozvoljeno samo aktuelnom cenovniku / ostale readonly
		Date danasnjiDatum = new Date();
		if (cenovnik.datumVazenja.after(danasnjiDatum)) {
			cenovnik.save();
		}
		show();
	}
	
	public static void delete(long id){
	    //logicko brisanje
		Cenovnik cenovnik = Cenovnik.findById(id);
		cenovnik.IsDeleted = true;
		cenovnik.save();
//		//fizicko brisanje
//		Cenovnik cenovnik = Cenovnik.findById(id);
//		cenovnik.delete();
		show();
	}
	
	public static void dodajStavku(double cena,String cenovnikId, String robaId) {
		StavkaCenovnika stavka = new StavkaCenovnika();
		stavka.cena = cena;
		stavka.cenovnik = Cenovnik.findById(Long.parseLong(cenovnikId));
		stavka.roba = Roba.findById(Long.parseLong(robaId));
		stavka.save();
	}
	
	//	ne vidim potrebu pretrage cenovnika sem po datumu vazenja |
	public static void searchByDate(String datum){
		List<Cenovnik> pp = Cenovnik.find("byIsDeletedAndDatumVazenja", 0, datum).fetch();
        renderTemplate("Dobavljac/Cenovnik/show.html", pp);
	}
}
