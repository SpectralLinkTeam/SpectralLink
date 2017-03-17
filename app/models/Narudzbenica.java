package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
public class Narudzbenica extends Model {

//	@OneToOne(mappedBy="narudzbenica",  cascade = {CascadeType.ALL})
//	public Company narucioc;
	
	@Column
	public Date datumNarudzbine;
	
	@Column
	public String komentar;
	
//	@OneToMany(mappedBy="narudzbenica")
//	public List<Roba> listaProizvoda;
	
	@Column(columnDefinition="tinyint default 0")
	public boolean IsDeleted;
	
	/*
	@OneToOne(mappedBy="narudzbenica",  cascade = {CascadeType.ALL})
	public Company dobavljac;

	@OneToOne(mappedBy="narudzbenica",  cascade = {CascadeType.ALL})
	public Company kupac;
	*/
	//== stavke narudzbenice
	
}
