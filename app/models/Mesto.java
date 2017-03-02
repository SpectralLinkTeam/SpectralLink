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
	
	@OneToMany
	public List<Company> preduzeca;

	@OneToMany
	public List<BusinessPartner> poslovniPartneri;
	
	@Column(columnDefinition="tinyint(1) default 0")
	public boolean IsDeleted;
	
}
