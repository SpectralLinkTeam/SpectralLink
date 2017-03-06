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
	
	@OneToMany(mappedBy="PDV")
	public List<StopaPDV> stopePDV;

	@OneToMany(mappedBy="PDV")
	public List<GrupaRobe> grupeRobe;
}
