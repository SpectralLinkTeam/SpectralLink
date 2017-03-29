package models;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

public class StavkaOtpremnice extends Model {
	
	@Column
	public int kolicina;
	
	@ManyToOne
	public Roba roba;
	
	@Column
	public double iznosStavke;
	
	@Column
	public String napomena;

}
