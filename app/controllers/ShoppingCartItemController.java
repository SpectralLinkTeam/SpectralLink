package controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import models.BusinessPartner;
import models.Narudzbenica;
import models.Roba;
import models.ShoppingCartItems;
import models.StavkaNarudzbenice;
import play.mvc.Controller;

public class ShoppingCartItemController extends Controller {

	public static void getAll(){
    	List<ShoppingCartItems> proizvodi = ShoppingCartItems.findAll();
    	//Cenovnik cenovnik = Cenovnik.find("order by datumVazenja desc").first();
    	//List<StavkaCenovnika> cene = StavkaCenovnika.find("byCenovnik", cenovnik.id).fetch();
		renderTemplate("Dobavljac/webshop/shoppingcart.html", proizvodi);
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
