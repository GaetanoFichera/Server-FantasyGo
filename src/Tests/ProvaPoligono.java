package Tests;

import Util.Coordinata;
import Util.ZonaDiCacciaUtil;

import java.util.ArrayList;

/**
 * Created by root on 11/10/17.
 */
public class ProvaPoligono {
    public static void main(String[] args) {

        Coordinata coordinata = new Coordinata(13.364295, 42.362331); //punto da controllare

        //poligono Area2
        Coordinata coordinata1 = new Coordinata(13.3360291,42.3763001);
        Coordinata coordinata2 = new Coordinata(13.3236694,42.3544847);
        Coordinata coordinata3 = new Coordinata(13.3511353,42.3554995);
        Coordinata coordinata4 = new Coordinata(13.3731079,42.3483953);
        Coordinata coordinata5 = new Coordinata(13.3858109,42.3727493);

        ArrayList<Coordinata> region = new ArrayList<>();
        region.add(coordinata1);
        region.add(coordinata2);
        region.add(coordinata3);
        region.add(coordinata4);
        region.add(coordinata5);

        boolean presente = ZonaDiCacciaUtil.coordinataInsideZona(region, coordinata);
        System.out.println(presente);
        }
    }




