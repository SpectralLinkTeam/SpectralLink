package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Roba extends Model {
	
	@Column
	public String naziv;
	
	@Column
	public String opis;
	
	@Column
	public String slika;
	
	@Column
	public String jedinicaMere;
	
	@Column(columnDefinition="tinyint(1) default 0")
	public boolean IsDeleted;
	
	@ManyToOne
	public GrupaRobe grupaRobe;
	
	@OneToMany
	public List<StavkaFakture> stavkeFakture;

	@OneToMany
	public List<StavkaCenovnika> stavkeCenovnika;
}
