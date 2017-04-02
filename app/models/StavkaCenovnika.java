package models;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import play.db.jpa.Model;

@Entity
@Table(name = "StavkaCenovnika")
public class StavkaCenovnika extends Model {

	@Column
	public double cena;
	
	@ManyToOne
	public Cenovnik cenovnik;
	
	@ManyToOne
	public Roba roba;

}