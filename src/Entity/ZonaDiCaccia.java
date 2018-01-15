package Entity;

import Util.Coordinata;
import org.apache.commons.lang.SerializationUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;

/**
 * Created by gaetano on 11/10/17.
 */
@Entity
@Table(name = "ZONEDICACCIA")
public class ZonaDiCaccia {
    @javax.persistence.Id
    @Column(name = "ZDC_ID")
    private String Id;
    @Column(name = "NOME", nullable = false, unique = true)
    private String Nome;
    @Column(name = "CENTRO", nullable = false)
    private byte[] CoordinataCentroAsByteArray;
    @Column(name = "CONFINI", nullable = false)
    private byte[] CoordinateConfiniAsByteArray;

    public ZonaDiCaccia() {}

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public byte[] getCoordinataCentroAsByteArray() {
        return CoordinataCentroAsByteArray;
    }

    public void setCoordinataCentroAsByteArray(byte[] coordinataCentroAsByteArray) {
        CoordinataCentroAsByteArray = coordinataCentroAsByteArray;
    }

    public Coordinata getCoordinataCentro(){
        return ((Coordinata) SerializationUtils.deserialize(CoordinataCentroAsByteArray));
    }

    public void setCoordinataCentro(Coordinata coordinata){
        CoordinataCentroAsByteArray = SerializationUtils.serialize(coordinata);
    }

    public byte[] getCoordinateConfiniAsByteArray() {
        return CoordinateConfiniAsByteArray;
    }

    public void setCoordinateConfiniAsByteArray(byte[] coordinateConfiniAsByteArray) {
        CoordinateConfiniAsByteArray = coordinateConfiniAsByteArray;
    }

    public ArrayList<Coordinata> getCoordinateConfini(){
        System.out.println("sono dentro");
        return ((ArrayList<Coordinata>) SerializationUtils.deserialize(CoordinateConfiniAsByteArray));
    }

    public void setCoordinateConfini(ArrayList<Coordinata> coordinateConfini){
        CoordinateConfiniAsByteArray = SerializationUtils.serialize(coordinateConfini);
    }
}
