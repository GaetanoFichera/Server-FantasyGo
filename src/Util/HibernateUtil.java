package Util;

import Entity.Giocatore;
import Entity.Mostro;
import Entity.Personaggio;
import Entity.ZonaDiCaccia;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.util.Properties;

/**
 * Created by gaetano on 11/10/17.
 */
public class HibernateUtil {

    //XML based configuration
    private static SessionFactory sessionFactory;

    private static ServiceRegistry registry;

    public static Session getSession(){
        if(sessionFactory == null) sessionFactory = buildSessionJavaConfigFactory();
        return sessionFactory.openSession();
    }

    private static SessionFactory buildSessionJavaConfigFactory() {
        try {
            Configuration configuration = new Configuration();

            //Create Properties, can be read from property files too
            Properties props = new Properties();
            props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            props.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/FantasyGo");
            props.put("hibernate.connection.username", "root");
            props.put("hibernate.connection.password", "mysql");
            props.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
            props.put("hibernate.hbm2ddl.auto", "update");
            props.put("hibernate.show_sql", "true");
            props.put("hibernate.format_sql", "true");
            //props.put("hibernate.temp.use_jdbc_metadata_defaults", "false");

            // c3p0 configuration
            props.put(Environment.C3P0_MIN_SIZE, 5);         //Minimum size of pool
            props.put(Environment.C3P0_MAX_SIZE, 30);        //Maximum size of pool
            props.put(Environment.C3P0_ACQUIRE_INCREMENT, 1);//Number of connections acquired at a time when pool is exhausted
            props.put(Environment.C3P0_TIMEOUT, 1800);       //Connection idle time
            props.put(Environment.C3P0_MAX_STATEMENTS, 150); //PreparedStatement cache size
            props.put(Environment.C3P0_CONFIG_PREFIX + ".initialPoolSize", 5); // i.e. 'hibernate.c3p0.initialPoolSize'
            props.put(Environment.C3P0_IDLE_TEST_PERIOD, 30);

            configuration.setProperties(props);

            configuration.addAnnotatedClass(Giocatore.class);
            configuration.addAnnotatedClass(ZonaDiCaccia.class);
            configuration.addAnnotatedClass(Mostro.class);
            configuration.addAnnotatedClass(Personaggio.class);

            registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Java Config ServiceRegistry created");

            sessionFactory = configuration.buildSessionFactory(registry);
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static Giocatore retrieveGiocatore(String idGiocatore){
        Giocatore giocatore = getSession().find(Giocatore.class, idGiocatore);

        return giocatore;
    }

    public static ZonaDiCaccia retrieveZonaDiCaccia(String idZonaDiCaccia){
        ZonaDiCaccia zonaDiCaccia = getSession().find(ZonaDiCaccia.class, idZonaDiCaccia);

        return zonaDiCaccia;
    }

    public static Personaggio retrievePersonaggio(String idPersonaggio){
        Personaggio personaggio = getSession().find(Personaggio.class, idPersonaggio);

        return personaggio;
    }

    public static Mostro retrieveMostro(String idMostro){
        Mostro mostro = getSession().find(Mostro.class, idMostro);

        return mostro;
    }
}
