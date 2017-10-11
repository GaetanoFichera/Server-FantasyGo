package Entity;

import javax.persistence.*;

/**
 * Created by gaetano on 11/10/17.
 */
@Entity
@Table(name = "GIOCATORI")
public class Giocatore {
    @javax.persistence.Id
    @Column(name = "G_ID")
    private String Id;
    @Column(name = "USERNAME", nullable = false)
    private String Username;
    @Column(name = "PASSWORD", nullable = false)
    private String Password;
    @ManyToOne
    @JoinColumn(name = "ZDC_ID", foreignKey = @ForeignKey(name = "ZDC_ID_FK"))
    private ZonaDiCaccia ZonaDiCacciaAssegnata;

    public Giocatore() {}

    public Giocatore(String username, String password, ZonaDiCaccia zonaDiCacciaAssegnata) {
        Username = username;
        Password = password;
        ZonaDiCacciaAssegnata = zonaDiCacciaAssegnata;
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
}
