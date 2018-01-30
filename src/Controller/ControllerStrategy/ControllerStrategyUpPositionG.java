package Controller.ControllerStrategy;

import Controller.IControllerStrategy;
import Entity.Giocatore;
import Entity.ZonaDiCaccia;
import Util.*;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11/10/17.
 */

public class ControllerStrategyUpPositionG implements IControllerStrategy {

    @Override
    public Messaggio eseguiRichiesta(Object pacchettoDalClient) {
        Messaggio messaggioRisposta = new Messaggio();
        ArrayList<String> datiDalGiocatore = ((ArrayList<String>) pacchettoDalClient);
        Coordinata coordinataDelGiocatore = new Coordinata();
        coordinataDelGiocatore.setLatitudine(Double.valueOf(datiDalGiocatore.get(1)));
        coordinataDelGiocatore.setLongitudine(Double.valueOf(datiDalGiocatore.get(2)));
        String idGiocatore = datiDalGiocatore.get(0);
        Session session = null;
        try{
            session = HibernateUtil.getSession();
            session.getTransaction().begin();
            Giocatore giocatore = HibernateUtil.retrieveGiocatore(idGiocatore);
            boolean tru = true;
            if (tru == true) {
                ZonaDiCaccia zonaDiCacciaGiocatore = HibernateUtil.retrieveZonaDiCaccia(giocatore.getZonaDiCacciaAssegnata().getId());
                if (!ZonaDiCacciaUtil.coordinataInsideZona(zonaDiCacciaGiocatore.getCoordinateConfini(), coordinataDelGiocatore)) {
                    List<ZonaDiCaccia> zone = session.createCriteria(ZonaDiCaccia.class).list();
                    ArrayList<ZonaDiCaccia> zoneArrayList = new ArrayList<>();
                    ArrayList<Coordinata> coordinateCentri = new ArrayList<>();
                    for (ZonaDiCaccia zona : zone) {
                        zoneArrayList.add(zona);
                        coordinateCentri.add(zona.getCoordinataCentro());
                    }
                    ZonaDiCaccia zonaNuova = ZonaDiCacciaUtil.areaContainingCoordinata(coordinateCentri, zoneArrayList, coordinataDelGiocatore);

                    if (zonaNuova != null) {//Il Giocatore ha cambiato Zona di Caccia
                        giocatore.setZonaDiCacciaAssegnata(HibernateUtil.retrieveZonaDiCaccia(zonaNuova.getId()));
                        session.saveOrUpdate(giocatore);
                        session.getTransaction().commit();
                        messaggioRisposta.setMessaggio(CodeResult.OkConAggiornamenti);
                        messaggioRisposta.setObject(zonaNuova.getId());
                        System.out.println("Idnuovazona" + zonaNuova.getId());
                    } else {
                        messaggioRisposta.setMessaggio(CodeResult.ErroreRitenta); //Il Giocatore non è in nessuna delle Zone di Caccia presenti nel Db
                    }
                    // (cosa succede quando il giocatore non è in nessuna delle zone esistenti?)
                } else messaggioRisposta.setMessaggio(CodeResult.OkSenzaAggiornamenti);
            }else messaggioRisposta.setMessaggio(CodeResult.IdSbagliato);
            //Il Giocatore non ha cambiato Zona di Caccia
        }catch (Exception e){
            if (session != null)
                if (session.getTransaction() != null)
                    session.getTransaction().rollback();
        }finally {
            if (session != null) session.close();
        }
        System.out.println(messaggioRisposta.toString());
        return messaggioRisposta;
    }

    private boolean checkDati(String idDati){
        boolean correct = false;
        int numberOfDigit = 0;
        int numberOfLetter = 0;
        for(int i=0; i<idDati.length() ; i++){
            if(Character.isDigit(idDati.charAt(i))) {
                numberOfDigit++;
                System.out.println("numerodiinteri:" + numberOfDigit);
            }
            else if (Character.isLetter(idDati.charAt(i))) numberOfLetter++;
            else return correct;
        }
        if(numberOfLetter == 1 && numberOfDigit == 5) return correct = true;
        else return correct;
    }

    private Messaggio applicaModifiche(Messaggio messaggio){
        return null;
    }
}