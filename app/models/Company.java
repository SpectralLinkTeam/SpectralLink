package models;

import play.db.jpa.Model;

public class Company extends Model {
	//naziv
	public String name;
	//adresa
	public String address;
	public String PIB;
	//telefon
	public String telephone;
	public String email;
	public String logo;
	
	public Company(){
		super();
	}

	public Company(String naziv, String adresa, String pIB, String telefon,
			String email, String logo) {
		super();
		this.name = naziv;
		this.address = adresa;
		PIB = pIB;
		this.telephone = telefon;
		this.email = email;
		this.logo = logo;
	}

}
