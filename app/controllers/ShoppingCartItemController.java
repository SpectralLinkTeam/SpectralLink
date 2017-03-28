package controllers;

import java.util.List;

import models.Roba;
import models.ShoppingCartItems;
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
}
