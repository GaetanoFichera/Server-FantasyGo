package Controller;

import com.fasterxml.jackson.databind.util.JSONPObject;

/**
 * Created by root on 11/10/17.
 */
public class ControllerFacade {

    public JSONPObject execute(String richiesta, JSONPObject jsonRichiesta){
        JSONPObject risposta = null;

        try {
            IControllerStrategy controllerStrategy = (IControllerStrategy) Class.forName("Controller.ControllerStrategy.ContollerStrategy" + richiesta).newInstance();
            risposta = controllerStrategy.richiesta(jsonRichiesta);
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
