package Util;

import Entity.Giocatore;
import Entity.ZonaDiCaccia;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

/**
 * Created by gaetano on 11/10/17.
 */
public class HibernateUtil {

    //XML based configuration
    private static SessionFactory sessionFactory;

    //Annotation based configuration
    private static SessionFactory sessionAnnotationFactory;

    //Property based configuration
    private static SessionFactory sessionJavaConfigFactory;

    private static ServiceRegistry registry;

    public static Session getSession(){
        sessionFactory = getSessionJavaConfigFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        return session;
    }

    /*
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure();
            System.out.println("Hibernate Configuration loaded");

            registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created");

            SessionFactory sessionFactory = configuration.buildSessionFactory(registry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    */

    /*
    private static SessionFactory buildSessionAnnotationFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate-annotation.cfg.xml");
            System.out.println("Hibernate Annotation Configuration loaded");

            registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Annotation serviceRegistry created");

            SessionFactory sessionFactory = configuration.buildSessionFactory(registry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    */

    private static SessionFactory buildSessionJavaConfigFactory() {
        try {
            Configuration configuration = new Configuration();

            //Create Properties, can be read from property files too
            Properties props = new Properties();
            props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            props.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/FantasyGo");
            props.put("hibernate.connection.username", "root");
            props.put("hibernate.connection.password", "");
            //props.put("c3p0.min_size", 1);
            //props.put("c3p0.max_size", 50);
            //props.put("hibernate.c3p0.timeout", 10);
            //props.put("hibernate.c3p0.max_statements", 50);
            props.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
            props.put("hibernate.hbm2ddl.auto", "update");
            props.put("hibernate.show_sql", "true");
            props.put("hibernate.format_sql", "true");

            configuration.setProperties(props);


            //we can set mapping file or class with annotation
            //addClass(Employee1.class) will look for resource
            // com/journaldev/hibernate/model/Employee1.hbm.xml (not good)
            configuration.addAnnotatedClass(Giocatore.class);
            configuration.addAnnotatedClass(ZonaDiCaccia.class);

            registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Java Config serviceRegistry created");

            SessionFactory sessionFactory = configuration.buildSessionFactory(registry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        //prima di chiudere controllare se il factory è diverso da null e quindi se è stato creato
        getSessionJavaConfigFactory().close();
    }

    /*
    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
    */

    /*
    public static SessionFactory getSessionAnnotationFactory() {
        if(sessionAnnotationFactory == null) sessionAnnotationFactory = buildSessionAnnotationFactory();
        return sessionAnnotationFactory;
    }
    */

    public static SessionFactory getSessionJavaConfigFactory() {
        if(sessionJavaConfigFactory == null) sessionJavaConfigFactory = buildSessionJavaConfigFactory();
        return sessionJavaConfigFactory;
    }

    public static Giocatore retrieveGiocatore(String idGiocatore){

        Giocatore giocatore = getSession().find(Giocatore.class, idGiocatore);
        return giocatore;
    }
    public static ZonaDiCaccia retrieveZonaDiCaccia(String idZonaDiCaccia){
        ZonaDiCaccia zonaDiCaccia= getSession().find(ZonaDiCaccia.class, idZonaDiCaccia);
        return zonaDiCaccia;
    }
}
