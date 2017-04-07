package controllers;

import play.mvc.Controller;

import java.util.Date;

/**
 * Created by alligator on 7.4.17..
 */
public class IzvestajiController extends Controller {

    public static void show(){

    }

    public static void generisi(Date datumOd, Date datumDo){
        System.out.print(datumDo);
    }
}
