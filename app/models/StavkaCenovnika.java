package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class StavkaCenovnika extends Model {

	@Column
	public double cena;
	
	@ManyToOne
	public Cenovnik cenovnik;
	
	@ManyToOne
	public Roba roba;

}
