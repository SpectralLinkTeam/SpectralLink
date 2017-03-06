package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class GrupaRobe extends Model {
	
	@Column
	public String naziv;
	
	@OneToMany(mappedBy="GrupaRobe")
	public List<Roba> robe;
	
//	@ManyToOne
//	public Cenovnik cenovnik;
	
	@ManyToOne
	public Company preduzece;
	
	@ManyToOne
	public PDV pdv;
	
	@Column(columnDefinition="tinyint(1) default 0")
	public boolean IsDeleted;

}
