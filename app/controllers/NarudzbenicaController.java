package controllers;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.StavkaNarudzbeniceDTO;
import models.*;
import play.mvc.Controller;
import viewmodels.NarudzbenicaViewModel;
import viewmodels.ShoppingCartViewModel;

public class NarudzbenicaController extends Controller {
// crud i search
	
	// da li je od nje generisana faktura ili nije
	// ako je generisana - read only
	
	public static void noveNarudzbenice(){
		List<Narudzbenica> narudzbenice = Narudzbenica.find("byFakturaGenerisanaAndIsDeleted", false,0).fetch();
		NarudzbenicaViewModel narudzbeniceViewModel = NarudzbenicaController.narudzbenice();
		renderTemplate("Dobavljac/narudzbenice.html", narudzbenice, narudzbeniceViewModel);
	}
	
	public static NarudzbenicaViewModel narudzbenice(){
		int sveNarudzbenice = Narudzbenica.find("byIsDeleted",0).fetch().size();
		int noveNarudzbenice = Narudzbenica.find("byFakturaGenerisanaAndIsDeleted", false,0).fetch().size();
		return new NarudzbenicaViewModel(sveNarudzbenice, noveNarudzbenice);
	}
	
	public static void searchJsonProductsById(long id){
		Narudzbenica narudzbenica = Narudzbenica.findById(id);
		List<StavkaNarudzbenice> robaNarudzbenice = narudzbenica.stavkeNarudzbenice;
		List<StavkaNarudzbeniceDTO> forNetwork = new ArrayList<>();

		for (StavkaNarudzbenice stavka : robaNarudzbenice){
			forNetwork.add(new StavkaNarudzbeniceDTO(stavka));
		}
		renderJSON(forNetwork);
	}
}
