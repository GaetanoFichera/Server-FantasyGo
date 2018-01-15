package Api;

import Controller.ControllerFacade;
import Entity.ZonaDiCaccia;
import Util.HibernateUtil;
import Util.CodeResult;
import Util.Messaggio;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaetano on 11/10/17.
 */
@Path("/ApiTest")
public class ApiTest {

    @GET
    @Produces("text/plain")
    public String getClichedMessage(){

        return TestGetDb();
    }

    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/prova")
    public Messaggio risposta(Messaggio messaggio){
        ArrayList<String> listaRicevuta = new ArrayList<>();
        listaRicevuta.add("G00001");
        //latitudine
        listaRicevuta.add("13.55026");
        //longitudine
        listaRicevuta.add("42.39557");
        ControllerFacade controllerFacade = new ControllerFacade();
        Messaggio risposta = controllerFacade.execute("UpPositionG", listaRicevuta);

        return risposta;
    }
    private String TestGetDb(){
        //questa riga sarà sostituita dalla varibile che giunge mediante il @Consumes
        ArrayList<String> listaRicevuta = new ArrayList<>();
        listaRicevuta.add("G00001");
        listaRicevuta.add("Gaetano");
        listaRicevuta.add("magari");
        listaRicevuta.add("ZDC002");
        //latitudine
        listaRicevuta.add("13.55026");
        //longitudine
        listaRicevuta.add("42.39557");

        //scrivere il test per riprendere i dati dal server
        ControllerFacade controllerFacade = new ControllerFacade();
        Messaggio risposta = controllerFacade.execute("UpPositionG", listaRicevuta);

        String stringaMessaggio = "nada";

        return CodeResult.getMessaggioErrore(risposta.getMessaggio());
    }

    private String RetrieveData(){
        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();

        Session session = sessionFactory.openSession();

        ZonaDiCaccia zona = null;

        try {
            List<ZonaDiCaccia> zonedicaccias = session.createQuery(
                    "select z " +
                            "from " + ZonaDiCaccia.class.getName() + " z ")
                    .list();

            for (ZonaDiCaccia zone : zonedicaccias) {
                zona = zone;

                System.out.println("ZoneDiCaccia: " + zone.getId() + " : "
                        + zone.getCoordinataCentro().getLatitudine() + " : "
                        + zone.getCoordinataCentro().getLongitudine() + " : "
                        + "Numero di Confini = " + zone.getCoordinateConfini().size() + " : "
                        + zone.getCoordinateConfini().toString()
                );
            }

            // Commit data.
            if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback in case of an error occurred.
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        HibernateUtil.shutdown();

        String risultato = "";
        if(zona != null) risultato ="ZoneDiCaccia: " + zona.getId() + " : "
                                + zona.getCoordinataCentro().getLatitudine() + " : "
                                + zona.getCoordinataCentro().getLongitudine() + " : "
                                + "Numero di Confini = " + zona.getCoordinateConfini().size() + " : "
                                + zona.getCoordinateConfini().toString();

        else risultato = "Non ho trovato niente";

        return risultato;
    }

}
