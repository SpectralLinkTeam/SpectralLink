package controllers;

import java.util.ArrayList;
import java.util.List;

import dto.GrupaRobeDTO;
import dto.MestoDTO;
import models.BusinessYear;
import models.GrupaRobe;
import models.Mesto;
import play.mvc.Controller;

public class MestoController extends Controller {
	
// crud i search
	
	public static void show(){
	    List<Mesto> lista = Mesto.findAll();
        renderTemplate("Dobavljac/Mesto/show.html", lista);
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
		List<Mesto> lista = Mesto.find("byNaziv", "%" + searchTerm + "%").fetch();
        renderTemplate("Dobavljac/Mesto/show.html", lista);
	}


	public static void searchAllJson(){
		List<Mesto> list = Mesto.findAll();
		List<MestoDTO> forNetwork = new ArrayList<>();
		for (Mesto mesto : list){
			forNetwork.add(new MestoDTO(mesto));
		}
		renderJSON(forNetwork);
	}
}
