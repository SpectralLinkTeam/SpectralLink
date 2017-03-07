package models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

@Entity
public class Narudzbenica extends Model {

	/*
	@OneToOne(mappedBy="narudzbenica",  cascade = {CascadeType.ALL})
	public Company dobavljac;

	@OneToOne(mappedBy="narudzbenica",  cascade = {CascadeType.ALL})
	public Company kupac;
	*/
	//== stavke narudzbenice
	
}
