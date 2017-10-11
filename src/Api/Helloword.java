package Api;

import Entity.ZonaDiCaccia;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by gaetano on 11/10/17.
 */
@Path("/helloword")
public class Helloword {
    @GET
    @Produces("text/plain")
    public String getClichedMessage(){
        return RetrieveData();
    }

    private String RetrieveData(){
        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();

        Session session = sessionFactory.openSession();

        ZonaDiCaccia zona = null;
        try {
            List<ZonaDiCaccia> zonedicaccias = session.createQuery(
                    "select z " +
                            "from " + ZonaDiCaccia.class.getName() + " z ")
                    .list();

            for (ZonaDiCaccia zone : zonedicaccias) {
                zona = zone;

                System.out.println("ZoneDiCaccia: " + zone.getId() + " : "
                        + zone.getCentroLatitudine() + " : "
                        + zone.getCentroLongitudine() + " : "
                        + zone.getCoordinataCentro().getLatitudine() + " : "
                        + zone.getCoordinataCentro().getLongitudine());
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

        String risultato = "";
        if(zona != null) risultato = "Giovanni Finocchio : " + zona.getId();
        else risultato = "Non ho trovato niente";

        return risultato;
    }

}
