package Controller;

import Util.Messaggio;

/**
 * Created by root on 11/10/17.
 */
public interface IControllerStrategy {
     Messaggio eseguiRichiesta(Object pacchettoDalClient);
}
