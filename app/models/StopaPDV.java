package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class StopaPDV extends Model {
	
	@Column
	public int procenat;
	
	@Column
	public Date datumVazenja;

}
