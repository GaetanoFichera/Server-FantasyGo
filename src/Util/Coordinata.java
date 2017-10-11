package Util;

import java.io.Serializable;

/**
 * Created by gaetano on 11/10/17.
 */
public class Coordinata implements Serializable{
    private static final long serialVersionUID = 6529685098267757690L;

    private Double Latitudine;
    private Double Longitudine;

    public Coordinata(Double latitudine, Double longitudine) {
        Latitudine = latitudine;
        Longitudine = longitudine;
    }

    public Double getLatitudine() {
        return Latitudine;
    }

    public void setLatitudine(Double latitudine) {
        Latitudine = latitudine;
    }

    public Double getLongitudine() {
        return Longitudine;
    }

    public void setLongitudine(Double longitudine) {
        Longitudine = longitudine;
    }

    @Override
    public String toString() {
        return "Coordinata{" +
                "Latitudine=" + Latitudine +
                ", Longitudine=" + Longitudine +
                '}';
    }
}
