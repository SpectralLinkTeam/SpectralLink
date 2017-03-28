package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
public class ShoppingCartItems extends Model {
	
	@OneToOne
	@JoinColumn(name = "roba_id")
	public Roba roba;
	
	@Column
	public int kolicina;

}
