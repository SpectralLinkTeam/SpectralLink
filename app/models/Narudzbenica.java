package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
public class Narudzbenica extends Model {
	
	@Column
	public Date datumNarudzbine;
	
	@ManyToOne
	public BusinessPartner kupac;
	
	@Column(columnDefinition="tinyint default 0")
	public boolean fakturaGenerisana;

	@Column(columnDefinition="tinyint default 0")
	public boolean IsDeleted;
	
	@OneToMany(mappedBy="narudzbenica")
	//@JoinColumn(name = "stavkeFakture_id")
	public List<StavkaNarudzbenice> stavkeNarudzbenice;
	
}
