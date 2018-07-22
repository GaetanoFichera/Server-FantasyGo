import Api.ApiGiocatore;
import Api.ApiTest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gaetano on 11/10/17.
 */
@ApplicationPath("/")
public class MainConnection extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(ApiTest.class);
        h.add(ApiGiocatore.class);
        return h;
    }
}
