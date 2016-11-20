package models;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Roba extends Model {
	
	@Column
	public String naziv;
	
	@Column
	public String jedinicaMere;
	
	@Column
	public GrupaRobe grupaRobe;

}
