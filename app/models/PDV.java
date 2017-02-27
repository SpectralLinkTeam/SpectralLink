package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class PDV extends Model {
	
	@Column
	public String naziv;
	
	@OneToMany
	public List<StopaPDV> stopePDV;

	@OneToMany
	public List<GrupaRobe> grupeRobe;
}
