package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

public class ShoppingCart extends Model {
	
	@OneToMany(mappedBy="ShoppingCart")
	public List<ShoppingCartItems> stavkeKorpe;

}
