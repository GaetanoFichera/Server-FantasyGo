package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by ASUS on 09/03/2017.
 */

@Entity
@Table(name = "MOSTRI")
public class Mostro extends Combattente implements Cloneable {

    @Column(name = "RICOMPENSA")
    private int ricompensa;
    @ManyToMany(mappedBy = "mostri")
    private Set<ZonaDiCaccia> zoneDiCaccia;

    public Mostro(String id, Caratteristiche caratteristiche, Equipaggiamento equipaggiamento, int ricompensa) {
        super(id, caratteristiche, equipaggiamento);
        this.ricompensa = ricompensa;
    }

    public int getRicompensa() {
        return ricompensa;
    }

    public void setRicompensa(int ricompensa) {
        this.ricompensa = ricompensa;
    }

    public Set<ZonaDiCaccia> getZoneDiCaccia() {
        return zoneDiCaccia;
    }

    @Override protected Object clone() {
        try {
            final Mostro result = (Mostro) super.clone();
            // copy fields that need to be copied here!
            return result;
        } catch (final CloneNotSupportedException ex) {
            throw new AssertionError();
        }
    }

    public void revive(){
        this.getCaratteristiche().setPuntiFerita(this.getCaratteristiche().getPuntiFeritaMax());
    }
}
