package models;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class GrupaRobe extends Model {
	
	@Column
	public String naziv;

}
