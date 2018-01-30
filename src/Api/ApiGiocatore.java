package Api;

import Controller.ControllerFacade;
import Util.Messaggio;
import Util.Richiesta;
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
    @Path("/updateLocation")
    public Messaggio updateGiocatoreLocation(Messaggio messaggio) {
        ControllerFacade controllerFacade = new ControllerFacade();
        Messaggio risposta = controllerFacade.execute(Richiesta.UP_POSITION_GIOCATORE, messaggio);

        return risposta;
    }
}