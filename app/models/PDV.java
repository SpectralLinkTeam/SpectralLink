package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name = "PDV")
public class PDV extends Model {
	
	@Column
	public String naziv;
	
	@OneToMany(mappedBy="pdv")
	public List<StopaPDV> stopePDV;

	@OneToMany(mappedBy="pdv")
	public List<GrupaRobe> grupeRobe;
}
