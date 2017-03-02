package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class StopaPDV extends Model {
	
	@Column
	public int procenat;
	
	@Column
	public Date datumVazenja;
	
	@ManyToOne
	public PDV pdv;
	
	@Column(columnDefinition="tinyint(1) default 0")
	public boolean IsDeleted;
	
}
