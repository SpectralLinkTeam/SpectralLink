package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name = "Mesto")
public class Mesto extends Model {

	@Column
	public String naziv;
	
	//================== dodati atribute koji su neophodni za mesto
	
	@OneToMany(mappedBy="mesto")
	public List<Company> preduzeca;

	@OneToMany(mappedBy="mesto")
	public List<BusinessPartner> poslovniPartneri;
	
}
