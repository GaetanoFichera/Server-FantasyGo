import Entity.ZonaDiCaccia;
import Util.Coordinata;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaetano on 12/10/17.
 */
public class SaveDataTest {
    public static void main(String[] args) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionJavaConfigFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            ZonaDiCaccia zonaDiCaccia = new ZonaDiCaccia();

            zonaDiCaccia.setId("ZDC002");

            zonaDiCaccia.setNome("Ponte della Speranza");

            Coordinata coordinataCentro = new Coordinata(13.2457352,42.4021645);
            zonaDiCaccia.setCoordinataCentro(coordinataCentro);

            ArrayList<Coordinata> coordinateConfini = new ArrayList<>();

            Coordinata coordinata1 = new Coordinata(13.2227364,42.4338044);
            coordinateConfini.add(coordinata1);
            Coordinata coordinata2 = new Coordinata(13.1856537,42.4249771);
            coordinateConfini.add(coordinata2);
            Coordinata coordinata3 = new Coordinata(13.2041931,42.3945584);
            coordinateConfini.add(coordinata3);
            Coordinata coordinata4 = new Coordinata(13.2845816,42.3624804);
            coordinateConfini.add(coordinata4);
            Coordinata coordinata5 = new Coordinata(13.3076757,42.3989464);
            coordinateConfini.add(coordinata5);
            Coordinata coordinata6 = new Coordinata(13.283844,42.4067277);
            coordinateConfini.add(coordinata6);
            Coordinata coordinata7 = new Coordinata(13.2227364,42.4338044);
            coordinateConfini.add(coordinata7);

            zonaDiCaccia.setCoordinateConfini(coordinateConfini);

            session.save(zonaDiCaccia);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        HibernateUtil.shutdown();
    }
}
