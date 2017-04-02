package dto;

import models.Roba;

/**
 * Created by alligator on 30.3.17..
 */
public class RobaDTO {

    private long id;

    private String naziv;

    private double cena;

    private String jedinicaMere;

    private int raspKol;

    private String opis;

    private long grupaRobe;

    // Clone constructor
    public RobaDTO(Roba roba) {
        this.id = roba.id;
        this.naziv = roba.naziv;
        this.cena = roba.stavkeCenovnika.get(0).cena;
        this.jedinicaMere = roba.jedinicaMere;
        this.raspKol = roba.raspKol;
        this.opis = roba.opis;
        this.grupaRobe = roba.grupaRobe.id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getJedinicaMere() {
        return jedinicaMere;
    }

    public void setJedinicaMere(String jedinicaMere) {
        this.jedinicaMere = jedinicaMere;
    }

    public int getRaspKol() {
        return raspKol;
    }

    public void setRaspKol(int raspKol) {
        this.raspKol = raspKol;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public long getGrupaRobe() {
        return grupaRobe;
    }

    public void setGrupaRobe(long grupaRobe) {
        this.grupaRobe = grupaRobe;
    }
}
