package controllers;

import models.Company;
import play.mvc.Controller;

import java.util.ArrayList;

/**
 * Created by alligator on 25.12.16..
 */
public class Dobavljac extends Controller{

    public static void redirect(){

        // ovde cemo povuci iz baze dobavljaca tipa Company
        Company company = new Company();
        renderTemplate("Dobavljac/dobavljac.html", company);
    }
}
