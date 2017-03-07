package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class ShoppingCart extends Model {
	
	@OneToMany(mappedBy="shoppingCart")
	public List<ShoppingCartItems> stavkeKorpe;



}
