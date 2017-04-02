package controllers;

import java.util.List;

import models.Narudzbenica;
import play.mvc.Controller;
import viewmodels.NarudzbenicaViewModel;

public class FakturaController extends Controller {
//crud i search
	// generisanje fakture na osnovu narudzbenice
	public static void generisiFakturu(long id){
		render();
	}
	
	// ..... fakture koje su zakljucene se ne mogu menjati - READONLY
	
	
}
