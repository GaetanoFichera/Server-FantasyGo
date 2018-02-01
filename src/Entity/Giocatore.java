package Entity;

import javax.persistence.*;
import java.util.*;

/**
 * Created by gaetano on 11/10/17.
 */
@Entity
@Table(name = "GIOCATORI")
public class Giocatore {

    @javax.persistence.Id
    @Column(name = "G_ID")
    private String Id;
    @Column(name = "STATO", nullable = false)
    private int Stato;
    @Column(name = "USERNAME", nullable = false)
    private String Username;
    @Column(name = "PASSWORD", nullable = false)
    private String Password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ZDC_ID", foreignKey = @ForeignKey(name = "ZDC_ID_FK"))
    private ZonaDiCaccia ZonaDiCacciaAssegnata;
    @OneToMany (mappedBy = "giocatore")
    private Collection<Personaggio> Personaggi;

    public Giocatore() {}

    public Giocatore(String id, String username, String password, ZonaDiCaccia zonaDiCacciaAssegnata) {
        this.Id = id;
        this.Stato = 0;
        Username = username;
        Password = password;
        ZonaDiCacciaAssegnata = zonaDiCacciaAssegnata;
        this.Personaggi = new LinkedHashSet<>();
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public ZonaDiCaccia getZonaDiCacciaAssegnata() {
        return ZonaDiCacciaAssegnata;
    }

    public void setZonaDiCacciaAssegnata(ZonaDiCaccia zonaDiCacciaAssegnata) {
        ZonaDiCacciaAssegnata = zonaDiCacciaAssegnata;
    }

    public Collection<Personaggio> getPersonaggi() {
        return Personaggi;
    }

    public void setPersonaggi(Collection<Personaggio> personaggi) {
        Personaggi = personaggi;
    }

    public int getStato() {
        return Stato;
    }

    public void setStato(int stato) {
        Stato = stato;
    }

    public  void addPersonaggio(Personaggio personaggio){
        this.Personaggi.add(personaggio);
    }
}
