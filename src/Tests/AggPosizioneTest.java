package Tests;

import Controller.ControllerFacade;
import Entity.ZonaDiCaccia;
import Util.CodeResult;
import Util.Messaggio;

import java.util.ArrayList;

/**
 * Created by gaetano on 12/10/17.
 */
public class AggPosizioneTest {
    public static void main(String[] args){
        //questa riga sarà sostituita dalla varibile che giunge mediante il @Consumes
        ArrayList<String> listaRicevuta = new ArrayList<>();
        listaRicevuta.add("G00001");
        listaRicevuta.add("Gaetano");
        listaRicevuta.add("magari");
        listaRicevuta.add("ZDC002");
        //latitudine
        listaRicevuta.add("13.38478");
        //longitudine
        listaRicevuta.add("42.31641");

        //scrivere il test per riprendere i dati dal server
        ControllerFacade controllerFacade = new ControllerFacade();
        Messaggio risposta = controllerFacade.execute("UpPositionG", listaRicevuta);

        System.out.println(CodeResult.getMessaggioErrore(risposta.getMessaggio()));
        if (risposta.getObject() != null) System.out.println(((ZonaDiCaccia) risposta.getObject()).getId());
    }
}
