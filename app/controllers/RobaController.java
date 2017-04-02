package controllers;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import dto.RobaDTO;
import models.Cenovnik;
import models.GrupaRobe;
import models.Roba;
import models.StavkaCenovnika;
import org.hibernate.*;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.jdbc.Work;
import org.hibernate.stat.SessionStatistics;
import play.mvc.Controller;

public class RobaController extends Controller {

	public static void show(){
	    Dobavljac.roba();
    }

	// CRUD entities

	public static void create(String grupaRobe, String naziv, double cena, int raspKol, String opis, String jedinicaMere){
		Roba roba = new Roba();
		roba.grupaRobe = GrupaRobe.findById(Long.parseLong(grupaRobe));
		roba.naziv = naziv;
		roba.raspKol = raspKol;
		roba.opis = opis;
		roba.jedinicaMere = jedinicaMere;
		roba.save();

		StavkaCenovnika sc = new StavkaCenovnika();
		sc.cena = cena;
		sc.cenovnik = Cenovnik.findById(1L);
		sc.roba = roba;
		sc.save();
		show();
	}
	
	public static void edit(long id, String grupaRobe, String naziv, double cena, int raspKol, String opis, String jedinicaMere){
		Roba roba = Roba.findById(id);

		roba.grupaRobe = GrupaRobe.findById(Long.parseLong(grupaRobe));
		roba.naziv = naziv;
		roba.raspKol = raspKol;
		roba.opis = opis;
		roba.jedinicaMere = jedinicaMere;
		roba.stavkeCenovnika.get(0).cena = cena;
		roba.save();
		show();
	}
	
	public static void delete(long id){
		Roba roba = Roba.findById(id);
		//logicko brisanje - problem (ne brise reference)
		//roba.IsDeleted = true;
		//roba.save();
		roba.delete();
		show();
	}
	
	public static void search(String searchTerm){
		List<Roba> lista = Roba.find("byIsDeletedAndNazivLikeAndOpisLike", "0", "%"+searchTerm+"%", "%"+searchTerm+"%").fetch();
        renderTemplate("Dobavljac/Roba/show.html", lista);
	}

	public static void searchJsonById(long id){
		Roba roba = Roba.findById(id);
		RobaDTO forNetwork = new RobaDTO(roba);
		renderJSON(forNetwork);
	}
}
