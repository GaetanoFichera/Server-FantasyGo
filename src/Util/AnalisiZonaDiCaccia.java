package Util;

import javax.swing.plaf.synth.Region;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by root on 11/10/17.
 */
public class AnalisiZonaDiCaccia {

    public AnalisiZonaDiCaccia() {
    }

    public boolean coordinateInRegion(ArrayList<Coordinata> region, Coordinata coord) {
        int i, j;
        boolean isInside = false;
        //create an array of coordinates from the region boundary list
        int sides = region.size();
        for (i = 0, j = sides - 1; i < sides; j = i++) {
            //verifying if your coordinate is inside your region
            if (
                    (
                            (
                                    (region.get(i).getLongitudine() <= coord.getLongitudine()) && (coord.getLongitudine() < region.get(j).getLongitudine())
                            ) || (
                                    (region.get(j).getLongitudine() <= coord.getLongitudine()) && (coord.getLongitudine() < region.get(i).getLongitudine())
                            )
                    ) &&
                            (coord.getLatitudine() < (region.get(j).getLatitudine() - region.get(i).getLatitudine()) * (coord.getLatitudine() -
                                    region.get(i).getLongitudine()) / (region.get(j).getLongitudine() - region.get(i).getLongitudine()) + region.get(i).getLatitudine())
                    ) {
                isInside = true;
            }
        }
        return isInside;
    }
}
