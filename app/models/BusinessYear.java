package models;

import play.db.jpa.Model;

public class BusinessYear extends Model {
	
	public int year;
	//zakljucena
	public boolean completed;
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
