package Controller.ControllerStrategy;

import Controller.ControllerFacade;
import Controller.IControllerStrategy;
import Entity.Giocatore;
import Entity.ZonaDiCaccia;
import Util.*;
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

        //Trasformo mediante Cast il Pacchetto Ricevuto dal Client in quello che io desidero
        ArrayList<String> datiDalGiocatore = ((ArrayList<String>) pacchettoDalClient);
        Coordinata coordinataDelGiocatore = new Coordinata();
        coordinataDelGiocatore.setLatitudine(Double.valueOf(datiDalGiocatore.get(1)));
        coordinataDelGiocatore.setLongitudine(Double.valueOf(datiDalGiocatore.get(2)));
        String idGiocatore = datiDalGiocatore.get(0);

        /*
        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();*/

        Session session = HibernateUtil.getSession();

        Giocatore giocatore = HibernateUtil.retrieveGiocatore(idGiocatore);
        System.out.println("Giocatore" + giocatore.getId());
        ZonaDiCaccia zonaDiCacciaGiocatore = HibernateUtil.retrieveZonaDiCaccia(giocatore.getZonaDiCacciaAssegnata().getId());
        //addConfini(zonaDiCacciaGiocatore, session);
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
                giocatore.setZonaDiCacciaAssegnata(HibernateUtil.retrieveZonaDiCaccia(zonaNuova.getId()));

                session.saveOrUpdate(giocatore);

                session.getTransaction().commit();

                messaggioRisposta.setMessaggio(CodeResult.OkConAggiornamenti);
                messaggioRisposta.setObject(zonaNuova);

                HibernateUtil.shutdown();
            } else{
                messaggioRisposta.setMessaggio(CodeResult.ErroreRitenta); //Il Giocatore non è in nessuna delle Zone di Caccia presenti nel Db
                HibernateUtil.shutdown();
            }
            // (cosa succede quando il giocatore non è in nessuna delle zone esistenti?)
        } else messaggioRisposta.setMessaggio(CodeResult.OkSenzaAggiornamenti); //Il Giocatore non ha cambiato Zona di Caccia
        HibernateUtil.shutdown();
        return messaggioRisposta;
    }
    public void addConfini(ZonaDiCaccia zonaDiCacciagiocatore, Session session){
        Coordinata coordinata1 = new Coordinata(13.3360291,42.3763001);
        Coordinata coordinata2 = new Coordinata(13.3236694,42.3544847);
        Coordinata coordinata3 = new Coordinata(13.3511353,42.3554995);
        Coordinata coordinata4 = new Coordinata(13.3731079,42.3483953);
        Coordinata coordinata5 = new Coordinata(13.3858109,42.3727493);

        ArrayList<Coordinata> region = new ArrayList<>();
        region.add(coordinata1);
        region.add(coordinata3);
        region.add(coordinata4);
        region.add(coordinata5);
        zonaDiCacciagiocatore.setCoordinateConfini(region);
        zonaDiCacciagiocatore.setCoordinataCentro(coordinata2);

        session.update(zonaDiCacciagiocatore);
    }

    public static void main(String[] args) {

        ArrayList<String> listaRicevuta = new ArrayList<>();

        listaRicevuta.add("G00001");
        //latitudine
        listaRicevuta.add("13.55026");
        //longitudine
        listaRicevuta.add("42.39557");

        //scrivere il test per riprendere i dati dal server
        ControllerFacade controllerFacade = new ControllerFacade();
        Messaggio risposta = controllerFacade.execute("UpPositionG", listaRicevuta);
        System.out.println(risposta);
    }
}


