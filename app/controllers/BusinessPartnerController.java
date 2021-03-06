package controllers;

import java.util.ArrayList;
import java.util.List;

import dto.BusinessPartnerDTO;
import dto.GrupaRobeDTO;
import models.BusinessPartner;
import models.GrupaRobe;
import models.Mesto;
import models.Roba;
import play.mvc.Controller;
import viewmodels.NarudzbenicaViewModel;

public class BusinessPartnerController extends Controller {

	public static void show(){
	    Dobavljac.poslovniPartneri();
    }
	
	public static void create(String naziv, long mesto, String adresa, String telefon, String email){
		BusinessPartner partner = new BusinessPartner();
		partner.name = naziv;
		//za sada ovako
		partner.mesto = Mesto.findById(mesto);
		partner.address = adresa;
		partner.phoneNumber = telefon;
		partner.email = email;

		partner.save();
	    show();
    }
	
	public static void edit(long id, String naziv, String mesto, String adresa, String telefon, String email){
		BusinessPartner partner = BusinessPartner.findById(id);
		partner.name = naziv;
		partner.mesto = Mesto.findById(Long.parseLong(mesto));
		partner.address = adresa;
		partner.phoneNumber = telefon;
		partner.email = email;

		partner.save();
		show();
	}
	
	public static void delete(long id){
	    //logicko brisanje
		BusinessPartner kupac = BusinessPartner.findById(id);
		kupac.IsDeleted = true;
		kupac.save();
		show();
	}
	
	public static void search(String searchTerm){
		List<BusinessPartner> pp = BusinessPartner.find("byIsDeletedAndNameLike", "0", "%"+searchTerm+"%").fetch();
		NarudzbenicaViewModel narudzbeniceViewModel = NarudzbenicaController.narudzbenice();

		renderTemplate("Dobavljac/poslovni-partneri.html", pp, narudzbeniceViewModel);
	}

	public static void searchJsonById(long id){
		BusinessPartner partner = BusinessPartner.findById(id);
		BusinessPartnerDTO forNetwork = new BusinessPartnerDTO(partner);
		renderJSON(forNetwork);
	}
	
	public static void searchAllJson(){
		List<BusinessPartner> list = BusinessPartner.findAll();
		List<BusinessPartnerDTO> forNetwork = new ArrayList<>();
		for (BusinessPartner kupac : list){
			forNetwork.add(new BusinessPartnerDTO(kupac));
		}
		renderJSON(forNetwork);
	}
	
}
