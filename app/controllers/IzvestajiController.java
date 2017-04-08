package controllers;

import play.mvc.Controller;
import viewmodels.NarudzbenicaViewModel;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Faktura;
import models.Otpremnica;

/**
 * Created by alligator on 7.4.17..
 */
public class IzvestajiController extends Controller {

    public static void show(){
    	NarudzbenicaViewModel narudzbeniceViewModel = NarudzbenicaController.narudzbenice();
		renderTemplate("Dobavljac/izvestaji.html", narudzbeniceViewModel);

    }

    public static void generisi(Date datumOd, Date datumDo){
        List<Faktura> fakture = Faktura.find("datumFakture>=? and datumFakture<=?", datumOd, datumDo).fetch();
        NarudzbenicaViewModel narudzbeniceViewModel = NarudzbenicaController.narudzbenice();
        SimpleDateFormat dt1 = new SimpleDateFormat("dd.MM.yyyy");
        String datumOdStr = dt1.format(datumOd);
        String datumDoStr = dt1.format(datumDo);
        renderTemplate("Dobavljac/izvestaji.html", fakture, narudzbeniceViewModel, datumOdStr, datumDoStr);
    }
}
