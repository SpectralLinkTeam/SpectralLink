package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Company extends Model {
	
	//naziv
	@Column
	public String name;
	
	//adresa
	@Column
	public String address;
	
	@Column
	public String PIB;
	
	//telefon
	@Column
	public String telephone;
	
	@Column
	public String email;
	
	@Column
	public String logo;
	
	@ManyToOne
	public Mesto mesto;
	
	@OneToMany(mappedBy="Company")
	public List<BusinessPartner> poslovniPartneri;
	
	@OneToMany(mappedBy="Company")
	public List<Faktura> fakture;
	
	@OneToMany(mappedBy="Company")
	public List<Cenovnik> cenovnici;
	
	@OneToMany(mappedBy="Company")
	public List<GrupaRobe> grupeRobe;
	
	public Company(){
		super();
	}

	public Company(String naziv, String adresa, String pIB, String telefon,
			String email, String logo) {
		super();
		this.name = naziv;
		this.address = adresa;
		this.PIB = pIB;
		this.telephone = telefon;
		this.email = email;
		this.logo = logo;
	}

}
