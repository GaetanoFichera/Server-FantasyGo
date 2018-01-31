package Controller;

import Util.Messaggio;

/**
 * Created by root on 11/10/17.
 */
public class ControllerFacade {

    public Messaggio execute(String richiesta, Object object){
        Messaggio risposta = null;
        try {
            IControllerStrategy controllerStrategy = (IControllerStrategy) Class.forName("Controller.ControllerStrategy.ControllerStrategy" + richiesta).newInstance();
            System.out.println("Controller" + richiesta + " trovato");
            risposta = controllerStrategy.eseguiRichiesta(object);
        } catch (InstantiationException e) {
            System.out.println("InstantiationException");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Controller" + richiesta + " non trovato");
            new Exception("Controller" + richiesta + " non trovato");
        }

        return risposta;
    }
}
