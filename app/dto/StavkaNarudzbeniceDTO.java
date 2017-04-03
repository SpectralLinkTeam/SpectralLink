package dto;

import models.Narudzbenica;
import models.StavkaNarudzbenice;

import java.util.List;

/**
 * Created by alligator on 3.4.17..
 */
public class StavkaNarudzbeniceDTO {

    public String naziv;

    public double cena;

    public int kolicina;

    public double ukupnaCena;

    public StavkaNarudzbeniceDTO(StavkaNarudzbenice stavka) {
        this.naziv = stavka.roba.naziv;
        this.cena = stavka.roba.stavkeCenovnika.get(0).cena;
        this.kolicina = stavka.kolicina;
        this.ukupnaCena = stavka.roba.stavkeCenovnika.get(0).cena * stavka.kolicina;
    }
}
