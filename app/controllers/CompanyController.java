package controllers;

import models.Company;
import play.mvc.Controller;

public class CompanyController extends Controller {
//samo edit...
	public static void edit(Company preduzece){
		preduzece.save();
    }
}
