package dto;

import models.PDV;

/**
 * Created by alligator on 3.4.17..
 */
public class PdvDTO {

    public String naziv;

    public int procenat;

    public PdvDTO(PDV pdv) {
        this.naziv = pdv.naziv;
        this.procenat = pdv.stopePDV.get(0).procenat;
    }
}
