package dto;

import models.BusinessPartner;

/**
 * Created by alligator on 2.4.17..
 */
public class BusinessPartnerDTO {

    public long id;

    public String naziv;

    public String mesto;

    public String adresa;

    public String telefon;

    public String email;

    public BusinessPartnerDTO(BusinessPartner partner) {
        this.id = partner.id;
        this.naziv = partner.name;
        this.mesto = partner.mesto.naziv;
        this.adresa = partner.address;
        this.telefon = partner.phoneNumber;
        this.email = partner.email;
    }
}
