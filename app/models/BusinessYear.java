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
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},mappedBy="BusinessYear")
	public List<Faktura> fakture;
	
	public BusinessYear() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BusinessYear(int year, boolean completed) {
		super();
		this.year = year;
		this.completed = completed;
	}

}
