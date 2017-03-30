package viewmodels;

import models.*;

public class ShoppingCartViewModel {
	public long itemId;
	public Roba roba;
	public int kolicina;
	public double jedinicnaCena;
	public int procenatPdv;
	public double ukupno;
	
	public ShoppingCartViewModel(long itemId, Roba roba, int kolicina, double jedinicnaCena, int procenatPdv) {
		super();
		this.itemId = itemId;
		this.roba = roba;
		this.kolicina = kolicina;
		this.jedinicnaCena = jedinicnaCena;
		this.procenatPdv = procenatPdv;
		this.ukupno = Math.round(kolicina*jedinicnaCena*(1+(double)procenatPdv/100));
	}

	

}
