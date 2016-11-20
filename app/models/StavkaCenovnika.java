package models;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class StavkaCenovnika extends Model {

	@Column
	public double cena;

}
