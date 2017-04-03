package dto;

import models.Mesto;

/**
 * Created by alligator on 3.4.17..
 */
public class MestoDTO {

    public long id;

    public String naziv;

    public MestoDTO(Mesto mesto) {
        this.id = mesto.id;
        this.naziv = mesto.naziv;
    }
}
