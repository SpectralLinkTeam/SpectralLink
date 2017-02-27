package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Cenovnik extends Model {
	
	@Column
	public Date datumVazenja;
	
	@OneToMany
	public List<StavkaCenovnika> stavkeCenovnika;
	
	@ManyToOne
	public Company preduzece;

}
