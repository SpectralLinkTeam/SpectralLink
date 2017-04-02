package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class BusinessPartner extends Model {

	@Column
	public String name;
	
	@Column
	public String address;

	@Column
	public String phoneNumber;

	@Column
	public String email;
	
	//vrsta partnera
	@Column(length=2)
	public String type;
	
	@ManyToOne
	public Mesto mesto;
	
	@ManyToOne
	public Company preduzece;
	
	@Column(columnDefinition="tinyint default 0")
	public boolean IsDeleted;
	
	@OneToMany(mappedBy="poslovniPartneri")
	public List<Faktura> fakture;
	
	@OneToMany(mappedBy="kupac")
	public List<Narudzbenica> narudzbenice;
	
	@OneToMany(mappedBy="kupac")
	public List<Otpremnica> otpremnice;
	
	public BusinessPartner() {
		super();
	}

	public BusinessPartner(String name, String address, String type) {
		super();
		this.name = name;
		this.address = address;
		this.type = type;
	}

}
