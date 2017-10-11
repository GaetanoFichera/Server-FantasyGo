package Util;

import javax.swing.plaf.synth.Region;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by root on 11/10/17.
 */
public class AnalisiZonaDiCaccia {

    public static boolean pointIsInsideArea(ArrayList<Coordinata> confini, Coordinata punto) {
        int i;
        int j;
        boolean result = false;

        //x is Lat & y is Long
        for(i = 0, j = confini.size() - 1; i < confini.size(); j = i++){
            if ((confini.get(i).getLongitudine() > punto.getLongitudine()) != (confini.get(j).getLongitudine() > punto.getLongitudine()) &&
                    (punto.getLatitudine() < (confini.get(j).getLatitudine() - confini.get(i).getLatitudine()) * (punto.getLongitudine() - confini.get(i).getLongitudine()) / (confini.get(j).getLongitudine()-confini.get(i).getLongitudine()) + confini.get(i).getLatitudine())) {
                result = !result;
            }
        }
        return result;
    }
}
