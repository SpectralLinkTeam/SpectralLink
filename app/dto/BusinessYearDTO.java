package dto;

import models.BusinessYear;

/**
 * Created by alligator on 7.4.17..
 */
public class BusinessYearDTO {

    public int year;

    public BusinessYearDTO(BusinessYear year) {
        this.year = year.year;
    }
}
