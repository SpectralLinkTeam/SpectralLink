package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
public class Otpremnica extends Model {

	@Column
	public int brojOtpremnice;
	
	@Column
	public Date datumOtpremnice;
	
	@Column(columnDefinition="tinyint default 0")
	public boolean IsDeleted;
	
	@ManyToOne
	public BusinessYear poslovnaGodina;
	
	@ManyToOne
	public BusinessPartner kupac;
	
	@OneToMany(mappedBy="otpremnica", cascade = CascadeType.ALL)
	//@JoinColumn(name = "stavkeFakture_id")
	public List<StavkaOtpremnice> stavkeOtpremnice;
}
