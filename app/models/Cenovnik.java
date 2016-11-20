package models;

import java.util.Date;
import java.util.List;

import play.db.jpa.Model;

public class Cenovnik extends Model {
	
	public Date datumVazenja;
	
	public List<StavkaCenovnika> stavkeCenovnika;

}
