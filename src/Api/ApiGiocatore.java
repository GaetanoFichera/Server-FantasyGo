package Api;

import Controller.ControllerFacade;
import Util.Messaggio;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by gaetano on 11/10/17.
 */

@Path("/ApiGiocatore")
public class ApiGiocatore {

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/UpdateLocation")
    public Messaggio updateGiocatoreLocation(Messaggio messaggio) {
        System.out.println("Messaggio Ricevuto: " + messaggio.toString());

        Messaggio risposta = new Messaggio();

        try{
            ControllerFacade controllerFacade = new ControllerFacade();

            risposta  = controllerFacade.execute(ControllerFacade.UP_POSITION_GIOCATORE, messaggio.getObject());
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Messaggio Da Spedire: " + messaggio.toString());
        return risposta;
    }
}