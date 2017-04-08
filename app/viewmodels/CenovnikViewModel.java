package viewmodels;

import java.util.List;

import models.Roba;
import models.StavkaCenovnika;

public class CenovnikViewModel {

	public long cenovnikId;
	
	public List<Roba> svaRoba;
	
	public List<StavkaCenovnika> stavkeCenovnika;
	
	public long stariCenovnikId;

	public CenovnikViewModel(long cenovnikId, List<Roba> svaRoba, List<StavkaCenovnika> stavkeCenovnika,
			long stariCenovnikId) {
		super();
		this.cenovnikId = cenovnikId;
		this.svaRoba = svaRoba;
		this.stavkeCenovnika = stavkeCenovnika;
		this.stariCenovnikId = stariCenovnikId;
	}
	
	public CenovnikViewModel(List<Roba> svaRoba, List<StavkaCenovnika> stavkeCenovnika,
			long stariCenovnikId) {
		super();
		this.cenovnikId = 0;
		this.svaRoba = svaRoba;
		this.stavkeCenovnika = stavkeCenovnika;
		this.stariCenovnikId = stariCenovnikId;
	}

		
	
}
