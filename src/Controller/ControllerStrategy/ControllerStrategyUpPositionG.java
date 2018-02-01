package Controller.ControllerStrategy;

import Controller.IControllerStrategy;
import Entity.Giocatore;
import Entity.ZonaDiCaccia;
import Util.*;
import Util.DBInteraction.DBInteractionCommand.FindByIdCommand;
import Util.DBInteraction.DBInteractionCommandFactory.FindByIdCommandFactory;
import Util.DBInteraction.ExecutionerDBInteractionFactory;
import Util.DBInteraction.IExecutionerDBInteraction;
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
        IExecutionerDBInteraction executionerDBInteraction = null;
        System.out.println("Prima di try/catch");

        try{

            ExecutionerDBInteractionFactory executionerDBInteractionFactory = new ExecutionerDBInteractionFactory();
            System.out.println("Prima di getInstance");
            executionerDBInteraction = executionerDBInteractionFactory.getExecutionerDBIntectionInstance(ExecutionerDBInteractionFactory.HIBERNATE);
            System.out.println("Prima di Open");
            executionerDBInteraction.openExecution();

            FindByIdCommandFactory findByIdCommandFactory = new FindByIdCommandFactory();
            FindByIdCommand findByIdCommand = (FindByIdCommand) findByIdCommandFactory.getCommandInstance(FindByIdCommandFactory.HIBERNATE);
            findByIdCommand.init(idGiocatore, Giocatore.class);
            System.out.println("Executioner" + executionerDBInteraction.toString());
            System.out.println("COmando " + findByIdCommand);
            System.out.println("Sono prima del Command");
            List<Object> response = null;
            response = executionerDBInteraction.executeCommand(findByIdCommand);
            System.out.println("risultato"+ response);
            messaggioRisposta.setMessaggio(CodeResult.OkConAggiornamenti);
            messaggioRisposta.setObject(((Giocatore) response.get(0)).getId());
            System.out.println("Risposta" + messaggioRisposta);

        }catch (Exception e){
            System.out.println(e.toString());
            if (executionerDBInteraction != null) executionerDBInteraction.roolbackExecution();

        }finally{
            if (executionerDBInteraction != null) executionerDBInteraction.closeExecution();
        }

        return messaggioRisposta;
    }

    private boolean checkDati(String idDati){
        int numberOfDigit = 0;
        if(Character.isLetter(idDati.charAt(0))) {
            for (int i = 1; i < idDati.length(); i++) {
                if (Character.isDigit(idDati.charAt(i))) numberOfDigit++;
                else return false;
            }
        }else return false;
        if(numberOfDigit == 5) return true;
        else return false;
    }

    private Messaggio applicaModifiche(Messaggio messaggio){
        return null;
    }

    private Messaggio vecchioExecute(Object pacchettoDalClient){
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
            Giocatore giocatore = HibernateUtil.retrieveGiocatore(idGiocatore, session);
            boolean tru = true;
            if (tru == true) {
                ZonaDiCaccia zonaDiCacciaGiocatore = HibernateUtil.retrieveZonaDiCaccia(giocatore.getZonaDiCacciaAssegnata().getId(), session);
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
                        giocatore.setZonaDiCacciaAssegnata(HibernateUtil.retrieveZonaDiCaccia(zonaNuova.getId(), session));
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
}