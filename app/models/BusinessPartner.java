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
	
	//vrsta partnera
	@Column(length=2)
	public String type;
	
	@ManyToOne
	public Mesto mesto;
	
	@ManyToOne
	public Company preduzece;
	
	@Column(columnDefinition="tinyint(1) default 0")
	public boolean IsDeleted;
	
	@OneToMany(mappedBy="poslovniPartneri")
	public List<Faktura> fakture;
	
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
