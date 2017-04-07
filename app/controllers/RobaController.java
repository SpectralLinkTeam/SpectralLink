package controllers;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dto.GrupaRobeDTO;
import dto.RobaDTO;
import models.Cenovnik;
import models.GrupaRobe;
import models.Roba;
import models.StavkaCenovnika;
import org.hibernate.*;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.jdbc.Work;
import org.hibernate.stat.SessionStatistics;
import play.Play;
import play.mvc.Controller;
import viewmodels.NarudzbenicaViewModel;

public class RobaController extends Controller {

	public static final String IMG_PATH = "public/images/products/";

	public static void show(){
	    Dobavljac.roba();
    }

	// CRUD entities

	public static void create(File slika, String grupaRobe, String naziv, double cena, int raspKol, String opis, String jedinicaMere){
		Roba roba = new Roba();
		roba.grupaRobe = GrupaRobe.findById(Long.parseLong(grupaRobe));
		roba.naziv = naziv;
		roba.raspKol = raspKol;
		roba.opis = opis;
		roba.jedinicaMere = jedinicaMere;

		Path from = slika.toPath();
		Path to = Paths.get( IMG_PATH + slika.getName());

		try {
			Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
			roba.slika = slika.getName();
		} catch (IOException e) {
			e.printStackTrace();
		}

		roba.save();

		StavkaCenovnika sc = new StavkaCenovnika();
		sc.cena = cena;
		sc.cenovnik = Cenovnik.findById(1L);
		sc.roba = roba;
		sc.save();
		show();
	}
	
	public static void edit(File slika, long id, String grupaRobe, String naziv, double cena, int raspKol, String opis, String jedinicaMere){
		Roba roba = Roba.findById(id);

		roba.grupaRobe = GrupaRobe.findById(Long.parseLong(grupaRobe));
		roba.naziv = naziv;
		roba.raspKol = raspKol;
		roba.opis = opis;
		roba.jedinicaMere = jedinicaMere;
		roba.stavkeCenovnika.get(0).cena = cena;

		if (slika != null){
			Path from = slika.toPath();
			Path to = Paths.get( IMG_PATH + slika.getName());
			try {
				Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
				roba.slika = slika.getName();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

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
		List<Roba> roba = Roba.find("byIsDeletedAndNazivLikeAndOpisLike", "0", "%"+searchTerm+"%", "%"+searchTerm+"%").fetch();
		NarudzbenicaViewModel narudzbeniceViewModel = NarudzbenicaController.narudzbenice();
		renderTemplate("Dobavljac/roba.html", roba, narudzbeniceViewModel);
	}
	
	public static void searchAllJson(){
		List<Roba> list = Roba.findAll();
		List<RobaDTO> forNetwork = new ArrayList<>();
		for (Roba roba : list){
			forNetwork.add(new RobaDTO(roba));
		}
		renderJSON(forNetwork);
	}

	public static void searchJsonById(long id){
		Roba roba = Roba.findById(id);
		RobaDTO forNetwork = new RobaDTO(roba);
		renderJSON(forNetwork);
	}
}
