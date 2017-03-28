package models;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
public class StavkaNarudzbenice extends Model {

	@Column
	public int kolicina;
	
	@ManyToOne
	public Roba roba;
	
	@ManyToOne
	public Narudzbenica narudzbenica;
}
