package Entity;

import org.apache.commons.lang.SerializationUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Combattente {

    private static final String TAG = "MCombattente";

    @Id
    @Column(name = "ID")
    private String id = null;
    @Column(name = "CARATTERISTICHE")
    private byte[] caratteristicheAsByteArray;
    @Column(name = "EQUIPAGGIAMENTO")
    private byte[] equipaggiamentoAsByteArray;

    public Combattente(){}

    /*
    public Combattente(String id, Caratteristiche caratteristiche, Equipaggiamento equipaggiamento) {
        this.id = id;
        this.setCaratteristiche(caratteristiche);
        this.setEquipaggiamento(equipaggiamento);
    }
    */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getEquipaggiamentoAsByteArray() {
        return equipaggiamentoAsByteArray;
    }

    public void setEquipaggiamentoAsByteArray(byte[] equipaggiamentoAsByteArray) {
        this.equipaggiamentoAsByteArray = equipaggiamentoAsByteArray;
    }

    public Equipaggiamento getEquipaggiamento(byte[] equipaggiamentoAsByteArray){
        return ((Equipaggiamento) SerializationUtils.deserialize(equipaggiamentoAsByteArray));
    }

    public void setEquipaggiamento(Equipaggiamento equipaggiamento){
        this.equipaggiamentoAsByteArray = SerializationUtils.serialize(equipaggiamento);
    }

    public byte[] getCaratteristicheAsByteArray() {
        return caratteristicheAsByteArray;
    }

    public void setCaratteristicheAsByteArray(byte[] caratteristicheAsByteArray) {
        this.caratteristicheAsByteArray = caratteristicheAsByteArray;
    }

    public Caratteristiche getCaratteristiche(){
        return ((Caratteristiche) SerializationUtils.deserialize(caratteristicheAsByteArray));
    }

    public void setCaratteristiche(Caratteristiche caratteristiche){
        this.caratteristicheAsByteArray = SerializationUtils.serialize(caratteristiche);
    }

    @Override
    public String toString() {
        return "MCombattente{" +
                "id=" + id +
                ", caratteristiche=" + this.toString() +
                '}';
    }
}
