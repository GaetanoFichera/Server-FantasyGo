package Util;

/**
 * Created by root on 11/10/17.
 */
public class RispostaRichiesta {

    private String messaggio;
    private Object object;

    public RispostaRichiesta(String messaggio, Object object) {
        this.messaggio = messaggio;
        this.object = object;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
