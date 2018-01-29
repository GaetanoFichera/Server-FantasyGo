package Controller.ControllerStrategy;

import Controller.ControllerFacade;
import Controller.IControllerStrategy;
import Entity.Giocatore;
import Entity.ZonaDiCaccia;
import Util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11/10/17.
 */

public class ControllerStrategyUpPositionG implements IControllerStrategy{

    @Override
    public Messaggio eseguiRichiesta(Object pacchettoDalClient) {
        Messaggio messaggioRisposta = new Messaggio();

        Session session = null;

        try{
            //Trasformo mediante Cast il Pacchetto Ricevuto dal Client in quello che io desidero
            ArrayList<String> datiDalGiocatore = ((ArrayList<String>) pacchettoDalClient);
            Coordinata coordinataDelGiocatore = new Coordinata();
            coordinataDelGiocatore.setLatitudine(Double.valueOf(datiDalGiocatore.get(1)));
            coordinataDelGiocatore.setLongitudine(Double.valueOf(datiDalGiocatore.get(2)));
            String idGiocatore = datiDalGiocatore.get(0);

            session = HibernateUtil.getSession();
            session.getTransaction().begin();

            Giocatore giocatore = HibernateUtil.retrieveGiocatore(idGiocatore);
            System.out.println("Giocatore: " + giocatore.getId());

            ZonaDiCaccia zonaDiCacciaGiocatore = HibernateUtil.retrieveZonaDiCaccia(giocatore.getZonaDiCacciaAssegnata().getId());
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

                } else{
                    messaggioRisposta.setMessaggio(CodeResult.ErroreRitenta); //Il Giocatore non è in nessuna delle Zone di Caccia presenti nel Db
                }
                // (cosa succede quando il giocatore non è in nessuna delle zone esistenti?)
            } else messaggioRisposta.setMessaggio(CodeResult.OkSenzaAggiornamenti); //Il Giocatore non ha cambiato Zona di Caccia
        }catch (Exception e){
            if (session != null)
                if (session.getTransaction() != null)
                    session.getTransaction().rollback();
            System.out.println("Errore Controller: " + e);
        }finally {
            if (session != null) session.close();
        }
        /**
         * Dovrebbe essere eseguito ogni volta ma a quanto pare se lo eseguo e dopo rieffettuo la
         * richiesta non funziona
          */
        // HibernateUtil.shutdown();

        return messaggioRisposta;
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