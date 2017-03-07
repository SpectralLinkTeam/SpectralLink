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
	
	@Column(columnDefinition="tinyint(1) default 0")
	public boolean IsDeleted;

	@Column
	public Date datumValute;
	
	@Column
	public String osnovica;
	
	@Column
	public int ukupanPDV;
	
	@Column
	public double iznosZaPlacanje;
	
	@Column(length=2)
	public String statusFakture;
	
	@ManyToOne
	public BusinessPartner poslovniPartneri;
	
	@ManyToOne
	public BusinessYear poslovnaGodina;
	
	@ManyToOne
	public Company preduzece;

	@OneToMany(orphanRemoval = true)
	@JoinColumn(name = "stavkeFakture_id")
	public List<StavkaFakture> stavkeFakture;

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
