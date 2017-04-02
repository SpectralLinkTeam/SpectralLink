package controllers;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Cenovnik;
import models.GrupaRobe;
import models.Narudzbenica;
import models.PDV;
import models.ShoppingCartItems;
import models.StavkaCenovnika;
import models.StopaPDV;
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
	
	
}
