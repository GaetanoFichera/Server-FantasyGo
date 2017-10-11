package Controller;

import Util.RispostaRichiesta;
import com.fasterxml.jackson.databind.util.JSONPObject;

/**
 * Created by root on 11/10/17.
 */
public interface IControllerStrategy {

     RispostaRichiesta richiesta(Object rispostaRichiesta);
}
