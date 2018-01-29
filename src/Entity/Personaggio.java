package Entity;

import org.apache.commons.lang.SerializationUtils;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "PERSONAGGI")
public class Personaggio extends Combattente {

    @Column(name = "NOME", nullable = false)
    private String nome;
    @Column(name = "BOTTINO", nullable = false)
    private int bottino;
    @Column(name = "SESSO", nullable = false)
    private String sesso;
    @Column(name = "RAZZA", nullable = false)
    private String razza;
    @Column(name = "CLASSE", nullable = false)
    private String classe;
    @Column(name = "PUNTI_ESP", nullable = false)
    private int puntiEsperienza;
    @Column(name = "INVENTARIO", nullable = false)
    private byte[] inventarioAsByteArray;
    @Column(name = "ORO", nullable = false)
    private int oro;
    @ManyToOne
    @JoinColumn(name = "G_ID", nullable = false)
    private Giocatore giocatore;


    public Personaggio(String id, String nome, Caratteristiche caratteristiche, Equipaggiamento equipaggiamento, int bottino, String sesso,
                       String razza, String classe, int puntiEsperienza, ArrayList<String> inventario, int oro) {
        super(id, caratteristiche, equipaggiamento);
        this.bottino = bottino;
        this.sesso = sesso;
        this.razza = razza;
        this.classe = classe;
        this.puntiEsperienza = puntiEsperienza;
        this.setInventario(inventario);
        this.oro = oro;
        this.nome = nome;
    }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public int getBottino() {
        return bottino;
    }

    public void setBottino(int bottino) {
        this.bottino = bottino;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

   public int getPuntiEsperienza() {
        return puntiEsperienza;
    }

    public void setPuntiEsperienza(int puntiEsperienza) {
        this.puntiEsperienza = puntiEsperienza;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getRazza() {
        return razza;
    }

    public void setRazza(String razza) {
        this.razza = razza;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public void increaseOro(int oro){
        this.oro += oro;
    }

    public void increasePuntiEsperienza(int puntiEsperienza){
        this.puntiEsperienza += puntiEsperienza;
    }

    public byte[] getInventarioAsByteArray() {
        return inventarioAsByteArray;
    }

    public void setInventarioAsByteArray(byte[] inventarioAsByteArray) {
        this.inventarioAsByteArray = inventarioAsByteArray;
    }

    public ArrayList<String> getInventario(){
        return ((ArrayList<String>) SerializationUtils.deserialize(this.inventarioAsByteArray));
    }

    public void setInventario(ArrayList<String> inventario){
        this.inventarioAsByteArray = SerializationUtils.serialize(inventario);
    }

    public void addOnetoInventory(String id){
        this.getInventario().add(id);
    }

    public void deleteOneFromInventory(String id){ this.getInventario().remove(id); }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    public void setGiocatore(Giocatore giocatore) {
        this.giocatore = giocatore;
    }

    @Override
    public String toString() {
        return "MPersonaggio{" +
                "id=" + getId() +
                ", caratteristiche=" + getCaratteristiche().toString() +
                ", idGiocatore=" + getGiocatore() +
                '}';
    }
}
