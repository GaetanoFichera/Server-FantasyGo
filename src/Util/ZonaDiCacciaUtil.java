package Util;

import Entity.ZonaDiCaccia;

import javax.swing.plaf.synth.Region;
import java.awt.*;
import java.awt.List;
import java.awt.geom.Point2D;
import java.util.*;

/**
 * Created by root on 11/10/17.
 */
public class ZonaDiCacciaUtil {

    public static boolean coordinataInsideZona(ArrayList<Coordinata> confini, Coordinata punto) {
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

    private static ArrayList<Coordinata> coordinataSort(ArrayList<Coordinata> coordinateCentri, Coordinata punto){
        ArrayList<Coordinata> coordinateCentriSorted = new ArrayList<>();

        java.util.List<Point2D> points = arrayListToListCoordinata(coordinateCentri);

        Point2D myPoint = new Point2D.Double(punto.getLatitudine(),punto.getLongitudine());

        Collections.sort(points, createComparator(myPoint));

        double maxDistance = 2.0;
        int index = 0;
        for (Point2D p : points) {
            if (p.distanceSq(myPoint) > maxDistance * maxDistance)
            {
                break;
            }
            index++;
        }

        java.util.List<Point2D> result = points.subList(0, index);

        coordinateCentriSorted = listToArrayListCoordinata(result);

        return coordinateCentriSorted;
    }

    private static Comparator<Point2D> createComparator(Point2D p)
    {
        final Point2D finalP = new Point2D.Double(p.getX(), p.getY());
        return new Comparator<Point2D>()
        {
            @Override
            public int compare(Point2D p0, Point2D p1)
            {
                double ds0 = p0.distanceSq(finalP);
                double ds1 = p1.distanceSq(finalP);
                return Double.compare(ds0, ds1);
            }

        };
    }

    private static java.util.List<Point2D> arrayListToListCoordinata(ArrayList<Coordinata> coordinate){
        java.util.List<Point2D> points = new ArrayList<Point2D>();

        for (Coordinata coordinata : coordinate){
            points.add(new Point2D.Double(coordinata.getLatitudine(),coordinata.getLongitudine()));
        }

        return points;
    }

    private static ArrayList<Coordinata> listToArrayListCoordinata(java.util.List<Point2D> points){
        ArrayList<Coordinata> coordinate = new ArrayList<>();

        for(int i = 0; i < points.size(); i++){
            coordinate.add(new Coordinata(points.get(i).getX(), points.get(i).getY()));
        }

        return coordinate;
    }

    public static ZonaDiCaccia areaContainingCoordinata(ArrayList<Coordinata> centriNonOrdinati, ArrayList<ZonaDiCaccia> zone, Coordinata punto){

        System.out.println("Calcolo Nuova Zona: " + centriNonOrdinati + " " + zone + " " + punto);

        boolean trovata = false;
        ZonaDiCaccia zonaContainingCoordinata = null;
        ArrayList<Coordinata> centri = coordinataSort(centriNonOrdinati, punto);

        for (int i = 0; i < centri.size() && trovata == false; i++){
            for (int j = 0; j < zone.size() && trovata == false; j++){
                if (zone.get(j).getCoordinataCentro().getLatitudine().compareTo(centri.get(i).getLatitudine()) == 0 &&
                        zone.get(j).getCoordinataCentro().getLongitudine().compareTo(centri.get(i).getLongitudine()) == 0){
                    if (coordinataInsideZona(zone.get(j).getCoordinateConfini(), punto)){
                        trovata = true;
                        zonaContainingCoordinata = zone.get(j);
                    }
                }
            }
        }

        return zonaContainingCoordinata;
    }
}
