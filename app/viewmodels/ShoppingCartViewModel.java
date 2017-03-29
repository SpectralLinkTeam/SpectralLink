package viewmodels;

import models.*;

public class ShoppingCartViewModel {
	public Roba roba;
	public int kolicina;
	public double jedinicnaCena;
	public int procenatPdv;
	public double ukupno;
	
	public ShoppingCartViewModel(Roba roba, int kolicina, double jedinicnaCena, int procenatPdv) {
		super();
		this.roba = roba;
		this.kolicina = kolicina;
		this.jedinicnaCena = jedinicnaCena;
		this.procenatPdv = procenatPdv;
		this.ukupno = kolicina*jedinicnaCena*(1+(double)procenatPdv/100);
	}

	

}
