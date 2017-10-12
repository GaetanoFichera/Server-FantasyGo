package Controller.ControllerStrategy;

import Controller.IControllerStrategy;
import Entity.Giocatore;
import Entity.ZonaDiCaccia;
import Util.Coordinata;
import Util.HibernateUtil;
import Util.Messaggio;
import Util.ZonaDiCacciaUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11/10/17.
 */

public class ControllerStrategyUpPositionG implements IControllerStrategy{

    @Override
    public Messaggio eseguiRichiesta(Object pacchettoDalClient) {

        Messaggio messaggioRisposta = new Messaggio();

        //Casto il Pacchetto Ricevuto dal Client in quello che io desidero
        ArrayList<String> datiDalGiocatore = ((ArrayList<String>) pacchettoDalClient);
        Coordinata coordinataDelGiocatore = new Coordinata();
        coordinataDelGiocatore.setLatitudine(Double.valueOf(datiDalGiocatore.get(4)));
        coordinataDelGiocatore.setLongitudine(Double.valueOf(datiDalGiocatore.get(5)));
        String idGiocatore = datiDalGiocatore.get(0);

        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Giocatore giocatore = session.find(Giocatore.class, idGiocatore);

        ZonaDiCaccia zonaDiCacciaGiocatore = session.find(ZonaDiCaccia.class, giocatore.getZonaDiCacciaAssegnata().getId());

        if(!ZonaDiCacciaUtil.coordinataInsideZona(zonaDiCacciaGiocatore.getCoordinateConfini(), coordinataDelGiocatore)){
            List<ZonaDiCaccia> zone =  session.createCriteria(ZonaDiCaccia.class).list();
            ArrayList<ZonaDiCaccia> zoneArrayList = new ArrayList<>();
            ArrayList<Coordinata> coordinateCentri = new ArrayList<>();
            for (ZonaDiCaccia zona : zone){
                zoneArrayList.add(zona);
                coordinateCentri.add(zona.getCoordinataCentro());
            }
            ZonaDiCaccia zonaNuova = ZonaDiCacciaUtil.areaContainingCoordinata(coordinateCentri, zoneArrayList, coordinataDelGiocatore);

            if(zonaNuova != null){//Il Giocatore ha cambiato Zona di Caccia
                giocatore.setZonaDiCacciaAssegnata(session.find(ZonaDiCaccia.class, zonaNuova.getId()));

                session.saveOrUpdate(giocatore);

                session.getTransaction().commit();

                messaggioRisposta.setMessaggio(101);
                messaggioRisposta.setObject(zonaNuova);

                HibernateUtil.shutdown();
            } else messaggioRisposta.setMessaggio(102); //Il Giocatore non è in nessuna delle Zone di Caccia presenti nel Db
            // (cosa succede quando il giocatore non è in nessuna delle zone esistenti?)
        } else messaggioRisposta.setMessaggio(100); //Il Giocatore non ha cambiato Zona di Caccia

        return messaggioRisposta;
    }
}
