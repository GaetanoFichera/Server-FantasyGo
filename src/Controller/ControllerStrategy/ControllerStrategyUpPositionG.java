package Controller.ControllerStrategy;

import Controller.IControllerStrategy;
import Entity.Giocatore;
import Entity.ZonaDiCaccia;
import Util.*;
import Util.DBInteraction.DBInteractionCommand.FindAllCommand;
import Util.DBInteraction.DBInteractionCommand.FindByIdCommand;
import Util.DBInteraction.DBInteractionCommand.SaveOrUpdateCommand;
import Util.DBInteraction.DBInteractionCommandFactory.CommandFactory;
import Util.DBInteraction.ExecutionerDBInteractionFactory;
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

        if(checkDati(idGiocatore)) {
            ExecutionerDBInteractionFactory executionerDBInteractionFactory = new ExecutionerDBInteractionFactory();

            try {
                (executionerDBInteractionFactory.getExecutionerDBIntectionInstance(ExecutionerDBInteractionFactory.HIBERNATE)).openExecution();

                CommandFactory commandFactory = new CommandFactory();
                FindByIdCommand findByIdCommand = (FindByIdCommand) commandFactory.getCommandInstance(CommandFactory.FINDBYID, CommandFactory.HIBERNATE);
                findByIdCommand.init(idGiocatore, Giocatore.class);

                List<Object> response = (executionerDBInteractionFactory.getExecutionerDBIntectionInstance(ExecutionerDBInteractionFactory.HIBERNATE)).executeCommand(findByIdCommand);
                Giocatore giocatore = ((Giocatore) response.get(0));

                findByIdCommand.init(giocatore.getZonaDiCacciaAssegnata().getId(), ZonaDiCaccia.class);
                List<Object> responseForCoordinate = (executionerDBInteractionFactory.getExecutionerDBIntectionInstance(ExecutionerDBInteractionFactory.HIBERNATE)).executeCommand(findByIdCommand);
                ZonaDiCaccia zonaDiCaccia = ((ZonaDiCaccia) responseForCoordinate.get(0));

                if(!ZonaDiCacciaUtil.coordinataInsideZona(zonaDiCaccia.getCoordinateConfini(), coordinataDelGiocatore)){
                    FindAllCommand findAllCommand = (FindAllCommand) CommandFactory.getCommandInstance(CommandFactory.FINDALL, CommandFactory.HIBERNATE);
                    findAllCommand.init(giocatore.getZonaDiCacciaAssegnata().getClass());
                    List<Object> responseFIndAll = (executionerDBInteractionFactory.getExecutionerDBIntectionInstance(ExecutionerDBInteractionFactory.HIBERNATE)).executeCommand(findAllCommand);
                    List<ZonaDiCaccia> zone = new ArrayList<>();
                    for (Object zona : responseFIndAll){
                        zone.add(((ZonaDiCaccia) zona));
                    }
                    ArrayList<ZonaDiCaccia> zoneArrayList = new ArrayList<>();
                    ArrayList<Coordinata> coordinateCentri = new ArrayList<>();
                    for (ZonaDiCaccia zona : zone) {
                        zoneArrayList.add(zona);
                        coordinateCentri.add(zona.getCoordinataCentro());
                    }
                    ZonaDiCaccia zonaNuova = ZonaDiCacciaUtil.areaContainingCoordinata(coordinateCentri, zoneArrayList, coordinataDelGiocatore);

                    if (zonaNuova != null) {//Il Giocatore ha cambiato Zona di Caccia
                        findByIdCommand.init(zonaNuova.getId(), ZonaDiCaccia.class);
                        List<Object> risultato = (executionerDBInteractionFactory.getExecutionerDBIntectionInstance(ExecutionerDBInteractionFactory.HIBERNATE)).executeCommand(findByIdCommand);
                        giocatore.setZonaDiCacciaAssegnata(((ZonaDiCaccia) risultato.get(0)));

                        SaveOrUpdateCommand saveOrUpdateCommand = ((SaveOrUpdateCommand) CommandFactory.getCommandInstance(CommandFactory.SAVEORUPDATE, CommandFactory.HIBERNATE));
                        saveOrUpdateCommand.init(giocatore);
                        (executionerDBInteractionFactory.getExecutionerDBIntectionInstance(ExecutionerDBInteractionFactory.HIBERNATE)).executeCommand(saveOrUpdateCommand);

                        messaggioRisposta.setMessaggio(CodeResult.OkConAggiornamenti);
                        messaggioRisposta.setObject(zonaNuova.getId());
                    } else {
                        messaggioRisposta.setMessaggio(CodeResult.ErroreRitenta); //Il Giocatore non è in nessuna delle Zone di Caccia presenti nel Db
                    }
                } else messaggioRisposta.setMessaggio(CodeResult.OkSenzaAggiornamenti);
            } catch (Exception e) {
                System.out.println(e.toString());
                (executionerDBInteractionFactory.getExecutionerDBIntectionInstance(ExecutionerDBInteractionFactory.HIBERNATE)).roolbackExecution();

            } finally {
                (executionerDBInteractionFactory.getExecutionerDBIntectionInstance(ExecutionerDBInteractionFactory.HIBERNATE)).closeExecution();
            }
        }else messaggioRisposta.setMessaggio(CodeResult.IdSbagliato);

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
                //findById ->
                ZonaDiCaccia zonaDiCacciaGiocatore = HibernateUtil.retrieveZonaDiCaccia(giocatore.getZonaDiCacciaAssegnata().getId(), session);

                if (!ZonaDiCacciaUtil.coordinataInsideZona(zonaDiCacciaGiocatore.getCoordinateConfini(), coordinataDelGiocatore)) {
                    //findAll ->
                    List<ZonaDiCaccia> zone = session.createCriteria(ZonaDiCaccia.class).list();
                    ArrayList<ZonaDiCaccia> zoneArrayList = new ArrayList<>();
                    ArrayList<Coordinata> coordinateCentri = new ArrayList<>();
                    for (ZonaDiCaccia zona : zone) {
                        zoneArrayList.add(zona);
                        coordinateCentri.add(zona.getCoordinataCentro());
                    }
                    ZonaDiCaccia zonaNuova = ZonaDiCacciaUtil.areaContainingCoordinata(coordinateCentri, zoneArrayList, coordinataDelGiocatore);

                    if (zonaNuova != null) {//Il Giocatore ha cambiato Zona di Caccia
                        //findById ->
                        giocatore.setZonaDiCacciaAssegnata(HibernateUtil.retrieveZonaDiCaccia(zonaNuova.getId(), session));

                        //saveOrUpdate ->
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