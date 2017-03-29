package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class BusinessYear extends Model {
	
	@Column
	public int year;
	
	//zakljucena
	@Column
	public boolean completed;
	
	@Column(columnDefinition="int default 0")
	public int poslednjiBrFakture;
	
	@Column(columnDefinition="int default 0")
	public int poslednjiBrOtpremnice;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},mappedBy="poslovnaGodina")
	public List<Faktura> fakture;
	
	@OneToMany(mappedBy="poslovnaGodina")
	public List<Otpremnica> otpremnice;
	
	public BusinessYear() {
		super();
	}
	public BusinessYear(int year, boolean completed) {
		super();
		this.year = year;
		this.completed = completed;
	}

}
