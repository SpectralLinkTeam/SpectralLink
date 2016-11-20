package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Cenovnik extends Model {
	
	@Column
	public Date datumVazenja;
	
	public List<StavkaCenovnika> stavkeCenovnika;

}
