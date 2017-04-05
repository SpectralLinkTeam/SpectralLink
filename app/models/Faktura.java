package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
public class Faktura extends Model {
	
	@Column
	public int brojFakture;
	
	@Column
	public Date datumFakture;
	
	@Column(columnDefinition="tinyint default 0")
	public boolean IsDeleted;

	@Column
	public Date datumValute;
	
	@Column
	public double osnovica;
	
	@Column
	public double ukupanPDV;
	
	@Column
	public double iznosZaPlacanje;
	
	@Column(columnDefinition="tinyint default 0")
	public boolean fakturaIzdata;
	
	@ManyToOne
	public BusinessPartner poslovniPartneri;
	
	@ManyToOne
	public BusinessYear poslovnaGodina;
	
	@ManyToOne
	public Company preduzece;

	@OneToMany(mappedBy="faktura", cascade = CascadeType.ALL)
	//@JoinColumn(name = "stavkeFakture_id")
	public List<StavkaFakture> stavkeFakture;

	public Faktura(Date datumFakture, Date datumValute,
			double osnovica, double ukupanPDV, double iznosZaPlacanje,
			boolean fakturaIzdata) {
		super();
		this.datumFakture = datumFakture;
		this.datumValute = datumValute;
		this.osnovica = osnovica;
		this.ukupanPDV = ukupanPDV;
		this.iznosZaPlacanje = iznosZaPlacanje;
		this.fakturaIzdata = fakturaIzdata;
		
	}

	public Faktura() {
		super();
	}
	
	
	
	

}
