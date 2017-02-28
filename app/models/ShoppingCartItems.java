package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

public class ShoppingCartItems extends Model {
	
	@Column
	public Roba roba;
	
	@Column
	public int kolicina;
	
	@ManyToOne
	public ShoppingCart shoppingCart;

}
