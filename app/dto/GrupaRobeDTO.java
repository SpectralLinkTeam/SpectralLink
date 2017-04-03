package dto;

import models.GrupaRobe;

/**
 * Created by alligator on 3.4.17..
 */
public class GrupaRobeDTO {

    public long id;

    public String naziv;

    public String pdv;

    public int stopaPdv;

    public GrupaRobeDTO(GrupaRobe grupaRobe) {
        this.id = grupaRobe.id;
        this.naziv = grupaRobe.naziv;
        this.pdv = grupaRobe.pdv.naziv;
        this.stopaPdv = grupaRobe.pdv.stopePDV.get(0).procenat;
    }
}
