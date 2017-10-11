package Controller;

import Util.RispostaRichiesta;
import com.fasterxml.jackson.databind.util.JSONPObject;

/**
 * Created by root on 11/10/17.
 */
public class ControllerFacade {

    public RispostaRichiesta execute(String richiesta, Object object){
         RispostaRichiesta risposta = null;

        try {
            IControllerStrategy controllerStrategy = (IControllerStrategy) Class.forName("Controller.ControllerStrategy.ContollerStrategy" + richiesta).newInstance();
            risposta = controllerStrategy.richiesta(object);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            new Exception("Controller" + richiesta + " non trovato");
        }

        return risposta;
    }
}
