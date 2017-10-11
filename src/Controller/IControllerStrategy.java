package Controller;

import com.fasterxml.jackson.databind.util.JSONPObject;

/**
 * Created by root on 11/10/17.
 */
public interface IControllerStrategy {

     JSONPObject richiesta(JSONPObject jsonpObject);
}
