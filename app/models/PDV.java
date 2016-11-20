package models;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class PDV extends Model {
	
	@Column
	public String naziv;
	
	@Column
	public StopaPDV stopaPDV;

}
