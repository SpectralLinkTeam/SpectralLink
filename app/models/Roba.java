package models;

import play.db.jpa.Model;

public class Roba extends Model {
	
	public String naziv;
	
	public String jedinicaMere;
	
	public GrupaRobe grupaRobe;

}
