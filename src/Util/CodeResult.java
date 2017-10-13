package Util;

/**
 * Created by gaetano on 12/10/17.
 */
public class CodeResult {
    public static final int OkSenzaAggiornamenti = 100; //Operazione andata a buon fine senza aggiornamento dati
    public static final int OkConAggiornamenti = 101; //Operazione andata a buon fine con aggiornamento dati
    public static final int ErroreRitenta = 102; //Operazione fallita, ritentare

    public static String getMessaggioErrore(int codiceErrore){
        String messaggioErrore = null;

        if (codiceErrore == 100) messaggioErrore = "Risultato 100: Operazione andata a buon fine senza aggiornamento dati";
        else if (codiceErrore == 101) messaggioErrore = "Risultato 101: Operazione andata a buon fine con aggiornamento dati";
        else if (codiceErrore == 102) messaggioErrore = "Risultato 102: Operazione fallita, ritentare";
        else messaggioErrore = "Risultato " + codiceErrore + ": Codice Errore Sconosciuto";

        return messaggioErrore;
    }
}
