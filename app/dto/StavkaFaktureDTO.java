package dto;

import models.Faktura;
import models.StavkaFakture;

public class StavkaFaktureDTO {
	public long id;
	public long proizvod;
	public int kolicina;
	public int rabat;
	
	public StavkaFaktureDTO(StavkaFakture stavka) {
        this.id = stavka.id;
        this.proizvod = stavka.roba.id;
        this.kolicina = stavka.kolicina;
        this.rabat = stavka.rabat;
    }
}
