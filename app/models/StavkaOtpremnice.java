package models;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
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
