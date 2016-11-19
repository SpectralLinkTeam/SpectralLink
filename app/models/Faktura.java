package models;

import java.util.Date;

import play.db.jpa.Model;

public class Faktura extends Model {
	
	public int brojFakture;
	
	public Date datumFakture;

	public Date datumValute;
	
	public String osnovica;
	
	public int ukupanPDV;
	
	public double iznosZaPlacanje;
	
	public String statusFakture;

	public Faktura(int brojFakture, Date datumFakture, Date datumValute,
			String osnovica, int ukupanPDV, double iznosZaPlacanje,
			String statusFakture) {
		super();
		this.brojFakture = brojFakture;
		this.datumFakture = datumFakture;
		this.datumValute = datumValute;
		this.osnovica = osnovica;
		this.ukupanPDV = ukupanPDV;
		this.iznosZaPlacanje = iznosZaPlacanje;
		this.statusFakture = statusFakture;
	}

	public Faktura() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
