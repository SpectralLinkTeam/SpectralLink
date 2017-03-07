package models;

import java.util.List;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
@Table(name = "Roba")
public class Roba extends Model {
	
	@Column
	public String naziv;
	
	@Column
	public String opis;
	
	@Column
	public String slika;
	
	@Column
	public String jedinicaMere;
	
	@Column(columnDefinition="tinyint default 0")
	public boolean IsDeleted;
	
	@ManyToOne
	public GrupaRobe grupaRobe;
	
	@OneToMany(mappedBy="roba")
	public List<StavkaFakture> stavkeFakture;

	@OneToMany(mappedBy="roba")
	public List<StavkaCenovnika> stavkeCenovnika;
}
