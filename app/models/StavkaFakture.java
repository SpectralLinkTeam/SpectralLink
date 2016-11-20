package models;

import play.db.jpa.Model;

public class StavkaFakture extends Model {
	
	public int kolicina;
	
	public double jedinicnaCena;
	
	public double rabat;
	
	public double osnovicaPDV;
	
	public int procenatPDV;
	
	public double iznosPDV;
	
	public double iznosStavke;

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
		// TODO Auto-generated constructor stub
	}

}
