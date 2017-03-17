package models;

import java.util.List;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
@Table(name = "Company")
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
	
	@OneToMany(mappedBy="preduzece")
	public List<BusinessPartner> poslovniPartneri;
	
	@OneToMany(mappedBy="preduzece")
	public List<Faktura> fakture;
	
	@OneToMany(mappedBy="preduzece")
	public List<Cenovnik> cenovnici;
	
	@OneToMany(mappedBy="preduzece")
	public List<GrupaRobe> grupeRobe;
	
	//----------novo lista narudzbenica
//	@OneToMany(mappedBy="preduzece")
//	public List<Narudzbenica> listaNarudzbenica;
	//------------------------------------
	
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
