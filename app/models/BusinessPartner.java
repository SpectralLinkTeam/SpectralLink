package models;

import play.db.jpa.Model;

public class BusinessPartner extends Model {
	
	public String name;
	
	public String address;
	//vrsta partnera
	public String type;
	
	public BusinessPartner() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusinessPartner(String name, String address, String type) {
		super();
		this.name = name;
		this.address = address;
		this.type = type;
	}

}
