package controllers;

import java.util.HashMap;
import java.util.Map;

import models.Narudzbenica;
import play.mvc.Controller;
import viewmodels.NarudzbenicaViewModel;

public class NarudzbenicaController extends Controller {
// crud i search
	
	// da li je od nje generisana faktura ili nije
	// ako je generisana - read only
	
	
	public static NarudzbenicaViewModel narudzbenice(){
		int sveNarudzbenice = Narudzbenica.find("byIsDeleted",0).fetch().size();
		int noveNarudzbenice = Narudzbenica.find("byFakturaGenerisanaAndIsDeleted", false,0).fetch().size();
		return new NarudzbenicaViewModel(sveNarudzbenice, noveNarudzbenice);
	}
}
