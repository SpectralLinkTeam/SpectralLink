package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Mesto extends Model {

	@Column
	public String naziv;
	
	//================== dodati atribute koji su neophodni za mesto
	
	@OneToMany(mappedBy="Mesto")
	public List<Company> preduzeca;

	@OneToMany(mappedBy="Mesto")
	public List<BusinessPartner> poslovniPartneri;
	
}
