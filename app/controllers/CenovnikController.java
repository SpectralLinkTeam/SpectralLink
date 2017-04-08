package controllers;

import java.util.Date;
import java.util.List;

import models.Cenovnik;
import models.Roba;
import models.StavkaCenovnika;
import play.mvc.Controller;
import viewmodels.CenovnikViewModel;

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
	
	public static void dodajStavku(double cena, long cenovnikId, long robaId) {
		StavkaCenovnika stavka = new StavkaCenovnika();
		Cenovnik cenovnik =Cenovnik.findById(cenovnikId); //Long.parseLong(cenovnikId));
		stavka.cena = cena;
		stavka.cenovnik = cenovnik; // Cenovnik.findById(Long.parseLong(cenovnikId));
		stavka.roba = Roba.findById(robaId); //Long.parseLong(robaId));
		stavka.save();
		cenovnik.stavkeCenovnika.add(stavka);
		cenovnik.save();
	}
	
	public static void popuniCenovnikStavkama(CenovnikViewModel cenovnikViewModel) {
		Cenovnik noviCenovnik = new Cenovnik();
    	noviCenovnik.save();
    	cenovnikViewModel.cenovnikId = noviCenovnik.id;
		for (StavkaCenovnika stavka : cenovnikViewModel.stavkeCenovnika) {
			dodajStavku(stavka.cena, cenovnikViewModel.cenovnikId, stavka.roba.id);
		}
	}
	
	public static CenovnikViewModel popuniFormuStavkeCenovnika() {
    	List<Roba> roba = Roba.find("byIsDeleted", 0).fetch();
    	Cenovnik stariCenovnik = Cenovnik.find("order by datumVazenja desc").first();
//    	Cenovnik noviCenovnik = new Cenovnik();
//    	noviCenovnik.save();
    	List<StavkaCenovnika> stavke = StavkaCenovnika.find("byCenovnik_id", stariCenovnik.id).fetch();
		CenovnikViewModel cenovnikViewModel = new CenovnikViewModel(roba, stavke, stariCenovnik.id);
		return cenovnikViewModel;
	}
	
	//	ne vidim potrebu pretrage cenovnika sem po datumu vazenja |
	public static void searchByDate(String datum){
		List<Cenovnik> pp = Cenovnik.find("byIsDeletedAndDatumVazenja", 0, datum).fetch();
        renderTemplate("Dobavljac/Cenovnik/show.html", pp);
	}
}
