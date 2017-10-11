package Entity;

import Util.Coordinata;
import org.apache.commons.lang.SerializationUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
    @Column(name = "CENTRO_LAT", nullable = false)
    private Double CentroLatitudine;
    @Column(name = "CENTRO_LONG", nullable = false)
    private Double CentroLongitudine;
    @Column(name = "CENTRO", nullable = false)
    private byte[] CoordinataCentroAsByteArray;

    public ZonaDiCaccia() {}

    public ZonaDiCaccia(String nome, Double centroLatitudine, Double centroLongitudine) {
        Nome = nome;
        CentroLatitudine = centroLatitudine;
        CentroLongitudine = centroLongitudine;
    }

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

    public Double getCentroLatitudine() {
        return CentroLatitudine;
    }

    public void setCentroLatitudine(Double centroLatitudine) {
        CentroLatitudine = centroLatitudine;
    }

    public Double getCentroLongitudine() {
        return CentroLongitudine;
    }

    public void setCentroLongitudine(Double centroLongitudine) {
        CentroLongitudine = centroLongitudine;
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

}
