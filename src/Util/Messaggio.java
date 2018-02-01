package Util;

/**
 * Created by root on 11/10/17.
 */
public class Messaggio {
    public static final int OkSenzaAggiornamenti = 100; //Operazione andata a buon fine senza aggiornamento dati
    public static final int OkConAggiornamenti = 101; //Operazione andata a buon fine con aggiornamento dati
    public static final int ErroreRitenta = 102; //Operazione fallita, ritentare
    public static final int IdSbagliato = 103; //Operazione fallita, ritentare

    private int messaggio; // Il Messaggio è codificato in numeri: Ex. 100 = OK
    private Object object;

    public Messaggio() {}

    public Messaggio(Integer messaggio, Object object) {
        this.messaggio = messaggio;
        this.object = object;
    }

    public int getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(int messaggio) {
        this.messaggio = messaggio;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "Messaggio{" +
                "messaggio=" + messaggio +
                ", object=" + object +
                '}';
    }

    public static String getMessaggioErrore(int codiceErrore){
        String messaggioErrore = null;

        if (codiceErrore == 100) messaggioErrore = "Risultato 100: Operazione andata a buon fine senza aggiornamento dati";
        else if (codiceErrore == 101) messaggioErrore = "Risultato 101: Operazione andata a buon fine con aggiornamento dati";
        else if (codiceErrore == 102) messaggioErrore = "Risultato 102: Operazione fallita, ritentare";
        else messaggioErrore = "Risultato " + codiceErrore + ": Codice Errore Sconosciuto";

        return messaggioErrore;
    }
}
