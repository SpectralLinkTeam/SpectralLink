package models;

import java.util.List;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
@Table(name = "GrupaRobe")
public class GrupaRobe extends Model {
	
	@Column
	public String naziv;
	
	@OneToMany(mappedBy="grupaRobe")
	public List<Roba> robe;
	
//	@ManyToOne
//	public Cenovnik cenovnik;
	
	@ManyToOne
	public Company preduzece;
	
	@ManyToOne
	public PDV pdv;
	
	@Column(columnDefinition="tinyint default 0")
	public boolean IsDeleted;

}
