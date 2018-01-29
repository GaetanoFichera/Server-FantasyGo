package Entity;

import java.io.Serializable;

/**
 * Created by ASUS on 09/03/2017.
 */
public class Equipaggiamento implements Serializable{

    private String Arma;
    private String Armatura;

    public Equipaggiamento(String arma, String armatura) {
        Arma = arma;
        Armatura = armatura;
    }

    public String getArma() {
        return Arma;
    }

    public void setArma(String arma) {
        Arma = arma;
    }

    public String getArmatura() {
        return Armatura;
    }

    public void setArmatura(String armatura) {
        Armatura = armatura;
    }
}
