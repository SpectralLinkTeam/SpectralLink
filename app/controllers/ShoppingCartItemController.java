package controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import models.*;
import play.mvc.Controller;
import viewmodels.ShoppingCartViewModel;

public class ShoppingCartItemController extends Controller {

	public static void getAll(){
    	List<ShoppingCartItems> proizvodi = ShoppingCartItems.findAll();
    	Cenovnik cenovnik = Cenovnik.find("order by datumVazenja desc").first();
    	List<ShoppingCartViewModel> items = new ArrayList<ShoppingCartViewModel>();
    	for(ShoppingCartItems item : proizvodi){
    		GrupaRobe grupa = item.roba.grupaRobe;
    		PDV pdv = grupa.pdv;
    		StopaPDV pdvStopa = StopaPDV.find("byPdv_idAndOrderByDatumVazenjaDesc", pdv.id).first();
    		int stopa = pdvStopa.procenat;
    		StavkaCenovnika cena = StavkaCenovnika.find("byRoba_idAndCenovnik_id", item.roba.id, cenovnik.id).first();
    		double jedinicnaCena = cena.cena;
    		ShoppingCartViewModel scvm = new ShoppingCartViewModel(item.roba, item.kolicina, jedinicnaCena, stopa);
    		items.add(scvm);
    		System.out.println("kolicina: " + scvm.kolicina);
    		System.out.println("jedinicna cena: " + scvm.jedinicnaCena);
    		//System.out.println("pdv: " + scvm.procenatPdv);
    		System.out.println("ukupno: " + scvm.ukupno);
    	}
    	
    	//List<StavkaCenovnika> cene = StavkaCenovnika.find("byCenovnik", cenovnik.id).fetch();
		renderTemplate("Dobavljac/webshop/shoppingcart.html", items);
	}
	
	public static void addToCart(long productId, int kolicina){
		ShoppingCartItems item = new ShoppingCartItems();
		item.roba = Roba.findById(productId);
		item.kolicina = kolicina;
		item.save();
		getAll();
	}
	
	public static void deleteFromShoppingCart(long itemId){
		ShoppingCartItems item = ShoppingCartItems.findById(itemId);
		item.delete();
		getAll();
	}
	
	public static void zavrsiKupovinu(){
		List<ShoppingCartItems> proizvodi = ShoppingCartItems.findAll();
		Narudzbenica narudzbenica = new Narudzbenica();		
		narudzbenica.kupac = BusinessPartner.all().first();
		System.out.println("kupac je: " + narudzbenica.kupac.id);
		narudzbenica.datumNarudzbine = new Date();
		narudzbenica.stavkeNarudzbenice = new ArrayList<StavkaNarudzbenice>();
		narudzbenica.save();
		for (ShoppingCartItems item : proizvodi) {
			StavkaNarudzbenice stavka = new StavkaNarudzbenice();
			stavka.kolicina = item.kolicina;
			stavka.roba = item.roba;
			narudzbenica.stavkeNarudzbenice.add(stavka);
			stavka.narudzbenica=narudzbenica;
			stavka.save();
		}
		//narudzbenica.save();
		ShoppingCartItems.deleteAll();
		getAll();

	}
	
}
