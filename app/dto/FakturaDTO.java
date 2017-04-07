package dto;

import models.Faktura;
import models.Roba;

public class FakturaDTO {
	public long id;
	public long kupac;
	
	public FakturaDTO(Faktura faktura) {
        this.id = faktura.id;
        this.kupac = faktura.poslovniPartneri.id;
    }

}
