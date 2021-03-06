package controllers;

import java.util.ArrayList;
import java.util.List;

import dto.GrupaRobeDTO;
import models.GrupaRobe;
import models.PDV;
import play.mvc.Controller;
import viewmodels.NarudzbenicaViewModel;

public class GrupaRobeController extends Controller {
// crud i search
	
	public static void show(){
	    Dobavljac.grupeRobe();
    }
	
	public static void create(String naziv, long pdv){
		GrupaRobe grupaRobe = new GrupaRobe();
		grupaRobe.naziv = naziv;
		grupaRobe.pdv = PDV.findById(pdv);
		grupaRobe.save();
		show();
    }
	
	public static void edit(long id, String naziv, long pdv){
		GrupaRobe grupaRobe = GrupaRobe.findById(id);
		grupaRobe.naziv = naziv;
		grupaRobe.pdv = PDV.findById(pdv);
		grupaRobe.save();
		show();
	}
	
	public static void delete(long id){
	    //logicko brisanje
		GrupaRobe gr = GrupaRobe.findById(id);
		gr.delete();
		/*gr.IsDeleted = true;
		gr.save();*/
		show();
	}
	
	public static void search(String searchTerm){
		List<GrupaRobe> lista = GrupaRobe.find("byIsDeletedAndNazivLike", "0", "%"+searchTerm+"%").fetch();
		NarudzbenicaViewModel narudzbeniceViewModel = NarudzbenicaController.narudzbenice();

		renderTemplate("Dobavljac/grupe-robe.html", lista, narudzbeniceViewModel);
	}

	public static void searchJsonById(long id){
		GrupaRobe grupaRobe = GrupaRobe.findById(id);
		GrupaRobeDTO forNetwork = new GrupaRobeDTO(grupaRobe);
		renderJSON(forNetwork);
	}

	public static void searchAllJson(){
		List<GrupaRobe> list = GrupaRobe.findAll();
		List<GrupaRobeDTO> forNetwork = new ArrayList<>();
		for (GrupaRobe gRobe : list){
			forNetwork.add(new GrupaRobeDTO(gRobe));
		}
		renderJSON(forNetwork);
	}
}
