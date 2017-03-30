package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name = "StopaPDV")
public class StopaPDV extends Model {
	
	@Column
	public int procenat;
	
	@Column
	public Date datumVazenja;
	
	@ManyToOne
	public PDV pdv;
	
	@Column(columnDefinition="tinyint default 0")
	public boolean IsDeleted;
	
}
