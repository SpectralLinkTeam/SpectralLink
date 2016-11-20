package models;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class BusinessPartner extends Model {

	@Column
	public String name;
	
	@Column
	public String address;
	
	//vrsta partnera
	@Column(length=2)
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
