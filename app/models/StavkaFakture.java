package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
public class StavkaFakture extends Model {
	
	@Column
	public int kolicina;
	
	@Column
	public double jedinicnaCena;
	
	@Column
	public double rabat;
	
	@Column
	public double osnovicaPDV;
	
	@Column
	public int procenatPDV;
	
	@Column
	public double iznosPDV;
	
	@Column
	public double iznosStavke;
	
	@ManyToOne
	public Cenovnik cenovnik;
	
	@ManyToOne
	public Faktura faktura;
	
	@ManyToOne
	public Roba roba;
	
	@Column(columnDefinition="tinyint default 0")
	public boolean stornirano;

	public StavkaFakture(int kolicina, double jedinicnaCena, double rabat,
			double osnovicaPDV, int procenatPDV, double iznosPDV,
			double iznosStavke) {
		super();
		this.kolicina = kolicina;
		this.jedinicnaCena = jedinicnaCena;
		this.rabat = rabat;
		this.osnovicaPDV = osnovicaPDV;
		this.procenatPDV = procenatPDV;
		this.iznosPDV = iznosPDV;
		this.iznosStavke = iznosStavke;
	}

	public StavkaFakture() {
		super();
	}

}
