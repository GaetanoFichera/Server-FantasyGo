import Entity.ZonaDiCaccia;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.List;

/**
 * Created by gaetano on 08/10/17.
 */
public class RetrieveDataTest {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();

        Session session = sessionFactory.openSession();

        try {
            List<ZonaDiCaccia> zonedicaccias = session.createQuery(
                    "select z " +
                            "from " + ZonaDiCaccia.class.getName() + " z ")
                    .list();

            for (ZonaDiCaccia zone : zonedicaccias) {
                System.out.println("ZoneDiCaccia: " + zone.getId() + " : "
                        + zone.getCoordinataCentro().getLatitudine() + " : "
                        + zone.getCoordinataCentro().getLongitudine() + " : "
                        + "Numero di Confini = " + zone.getCoordinateConfini().size() + " : "
                        + zone.getCoordinateConfini().toString()
                );
            }

            // Commit data.
            if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback in case of an error occurred.
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        HibernateUtil.shutdown();
    }
}
