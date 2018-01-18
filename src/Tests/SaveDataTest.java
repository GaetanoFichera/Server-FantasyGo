package Tests;

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
            session = HibernateUtil.getSession();
            transaction = session.getTransaction();
            transaction.begin();

            //ZDC001
            ZonaDiCaccia zonaDiCaccia1 = new ZonaDiCaccia();

            zonaDiCaccia1.setId("ZDC001");

            zonaDiCaccia1.setNome("Area 1");

            Coordinata coordinataCentro1 = new Coordinata(13.2457352,42.4021645);
            zonaDiCaccia1.setCoordinataCentro(coordinataCentro1);

            ArrayList<Coordinata> coordinateConfini1 = new ArrayList<>();

            Coordinata coordinata11 = new Coordinata(13.2227364,42.4338044);
            coordinateConfini1.add(coordinata11);
            Coordinata coordinata12 = new Coordinata(13.1856537,42.4249771);
            coordinateConfini1.add(coordinata12);
            Coordinata coordinata13 = new Coordinata(13.2041931,42.3945584);
            coordinateConfini1.add(coordinata13);
            Coordinata coordinata14 = new Coordinata(13.2845816,42.3624804);
            coordinateConfini1.add(coordinata14);
            Coordinata coordinata15 = new Coordinata(13.3076757,42.3989464);
            coordinateConfini1.add(coordinata15);
            Coordinata coordinata16 = new Coordinata(13.283844,42.4067277);
            coordinateConfini1.add(coordinata16);
            Coordinata coordinata17 = new Coordinata(13.2227364,42.4338044);
            coordinateConfini1.add(coordinata17);

            zonaDiCaccia1.setCoordinateConfini(coordinateConfini1);

            //ZDC002
            ZonaDiCaccia zonaDiCaccia2 = new ZonaDiCaccia();

            zonaDiCaccia2.setId("ZDC002");

            zonaDiCaccia2.setNome("Area 2");

            Coordinata coordinataCentro2 = new Coordinata(13.3878708,42.4079952);
            zonaDiCaccia2.setCoordinataCentro(coordinataCentro2);

            ArrayList<Coordinata> coordinateConfini2 = new ArrayList<>();

            Coordinata coordinata21 = new Coordinata(13.3076757,42.3989464);
            coordinateConfini2.add(coordinata21);
            Coordinata coordinata22 = new Coordinata(13.3360291,42.3763001);
            coordinateConfini2.add(coordinata22);
            Coordinata coordinata23 = new Coordinata(13.4527588,42.3676763);
            coordinateConfini2.add(coordinata23);
            Coordinata coordinata24 = new Coordinata(13.4527588,42.4168669);
            coordinateConfini2.add(coordinata24);
            Coordinata coordinata25 = new Coordinata(13.3999813,42.462878);
            coordinateConfini2.add(coordinata25);
            Coordinata coordinata26 = new Coordinata(13.3511353,42.4320727);
            coordinateConfini2.add(coordinata26);
            Coordinata coordinata27 = new Coordinata(13.3076757,42.3989464);
            coordinateConfini2.add(coordinata27);

            zonaDiCaccia2.setCoordinateConfini(coordinateConfini2);

            //ZDC003
            ZonaDiCaccia zonaDiCaccia3 = new ZonaDiCaccia();

            zonaDiCaccia3.setId("ZDC003");

            zonaDiCaccia3.setNome("Area 3");

            Coordinata coordinataCentro3 = new Coordinata(13.3054733,42.3359609);
            zonaDiCaccia3.setCoordinataCentro(coordinataCentro3);

            ArrayList<Coordinata> coordinateConfini3 = new ArrayList<>();

            Coordinata coordinata31 = new Coordinata(13.2845816,42.3624804);
            coordinateConfini3.add(coordinata31);
            Coordinata coordinata32 = new Coordinata(13.2917404,42.3557532);
            coordinateConfini3.add(coordinata32);
            Coordinata coordinata33 = new Coordinata(13.274231,42.333423);
            coordinateConfini3.add(coordinata33);
            Coordinata coordinata34 = new Coordinata(13.2972336,42.3039756);
            coordinateConfini3.add(coordinata34);
            Coordinata coordinata35 = new Coordinata(13.3178329,42.3123543);
            coordinateConfini3.add(coordinata35);
            Coordinata coordinata36 = new Coordinata(13.3408356,42.3293621);
            coordinateConfini3.add(coordinata36);
            Coordinata coordinata37 = new Coordinata(13.3236694,42.3544847);
            coordinateConfini3.add(coordinata37);
            Coordinata coordinata38 = new Coordinata(13.2845816,42.3624804);
            coordinateConfini3.add(coordinata38);

            zonaDiCaccia3.setCoordinateConfini(coordinateConfini3);

            //ZDC004
            ZonaDiCaccia zonaDiCaccia4 = new ZonaDiCaccia();

            zonaDiCaccia4.setId("ZDC004");

            zonaDiCaccia4.setNome("Area 4");

            Coordinata coordinataCentro4 = new Coordinata(13.3116531,42.3742711);
            zonaDiCaccia4.setCoordinataCentro(coordinataCentro4);

            ArrayList<Coordinata> coordinateConfini4 = new ArrayList<>();

            Coordinata coordinata41 = new Coordinata(13.3236694,42.3544847);
            coordinateConfini4.add(coordinata41);
            Coordinata coordinata42 = new Coordinata(13.3360291,42.3763001);
            coordinateConfini4.add(coordinata42);
            Coordinata coordinata43 = new Coordinata(13.3076757,42.3989464);
            coordinateConfini4.add(coordinata43);
            Coordinata coordinata44 = new Coordinata(13.2845816,42.3624804);
            coordinateConfini4.add(coordinata44);
            Coordinata coordinata45 = new Coordinata(13.3236694,42.3544847);
            coordinateConfini4.add(coordinata45);

            zonaDiCaccia4.setCoordinateConfini(coordinateConfini4);

            //ZDC005
            ZonaDiCaccia zonaDiCaccia5 = new ZonaDiCaccia();

            zonaDiCaccia5.setId("ZDC005");

            zonaDiCaccia5.setNome("Area 5");

            Coordinata coordinataCentro5 = new Coordinata(13.3693314,42.3313926);
            zonaDiCaccia5.setCoordinataCentro(coordinataCentro5);

            ArrayList<Coordinata> coordinateConfini5 = new ArrayList<>();

            Coordinata coordinata51 = new Coordinata(13.3236694,42.3544847);
            coordinateConfini5.add(coordinata51);
            Coordinata coordinata52 = new Coordinata(13.3408356,42.3293621);
            coordinateConfini5.add(coordinata52);
            Coordinata coordinata53 = new Coordinata(13.3528519,42.3077842);
            coordinateConfini5.add(coordinata53);
            Coordinata coordinata54 = new Coordinata(13.4026337,42.3054991);
            coordinateConfini5.add(coordinata54);
            Coordinata coordinata55 = new Coordinata(13.4256363,42.3258086);
            coordinateConfini5.add(coordinata55);
            Coordinata coordinata56 = new Coordinata(13.3868408,42.3445891);
            coordinateConfini5.add(coordinata56);
            Coordinata coordinata57 = new Coordinata(13.3511353,42.3554995);
            coordinateConfini5.add(coordinata57);
            Coordinata coordinata58 = new Coordinata(13.3236694,42.3544847);
            coordinateConfini5.add(coordinata58);

            zonaDiCaccia5.setCoordinateConfini(coordinateConfini5);

            //ZDC006
            ZonaDiCaccia zonaDiCaccia6 = new ZonaDiCaccia();

            zonaDiCaccia6.setId("ZDC006");

            zonaDiCaccia6.setNome("Area 6");

            Coordinata coordinataCentro6 = new Coordinata(13.3521652,42.3643787);
            zonaDiCaccia6.setCoordinataCentro(coordinataCentro6);

            ArrayList<Coordinata> coordinateConfini6 = new ArrayList<>();

            Coordinata coordinata61 = new Coordinata(13.3360291,42.3763001);
            coordinateConfini6.add(coordinata61);
            Coordinata coordinata62 = new Coordinata(13.3236694,42.3544847);
            coordinateConfini6.add(coordinata62);
            Coordinata coordinata63 = new Coordinata(13.3511353,42.3554995);
            coordinateConfini6.add(coordinata63);
            Coordinata coordinata64 = new Coordinata(13.3731079,42.3483953);
            coordinateConfini6.add(coordinata64);
            Coordinata coordinata65 = new Coordinata(13.3858109,42.3727493);
            coordinateConfini6.add(coordinata65);
            Coordinata coordinata66 = new Coordinata(13.3360291,42.3763001); 
            coordinateConfini6.add(coordinata66);

            zonaDiCaccia6.setCoordinateConfini(coordinateConfini6);

            //ZDC007
            ZonaDiCaccia zonaDiCaccia7 = new ZonaDiCaccia();

            zonaDiCaccia7.setId("ZDC007");

            zonaDiCaccia7.setNome("Area 7");

            Coordinata coordinataCentro7 = new Coordinata(13.4084702,42.3547384);
            zonaDiCaccia7.setCoordinataCentro(coordinataCentro7);

            ArrayList<Coordinata> coordinateConfini7 = new ArrayList<>();

            Coordinata coordinata71 = new Coordinata(13.3858109,42.3727493); 
            coordinateConfini7.add(coordinata71);
            Coordinata coordinata72 = new Coordinata(13.3731079,42.3483953); 
            coordinateConfini7.add(coordinata72);
            Coordinata coordinata73 = new Coordinata(13.3868408,42.3445891); 
            coordinateConfini7.add(coordinata73);
            Coordinata coordinata74 = new Coordinata(13.4256363,42.3258086); 
            coordinateConfini7.add(coordinata74);
            Coordinata coordinata75 = new Coordinata(13.4527588,42.3676763); 
            coordinateConfini7.add(coordinata75);
            Coordinata coordinata76 = new Coordinata(13.3858109,42.3727493); 
            coordinateConfini7.add(coordinata76);

            zonaDiCaccia7.setCoordinateConfini(coordinateConfini7);

            //ZDC008
            ZonaDiCaccia zonaDiCaccia8 = new ZonaDiCaccia();

            zonaDiCaccia8.setId("ZDC008");

            zonaDiCaccia8.setNome("Area 8");

            Coordinata coordinataCentro8 = new Coordinata(13.425293,42.2950879);
            zonaDiCaccia8.setCoordinataCentro(coordinataCentro8);

            ArrayList<Coordinata> coordinateConfini8 = new ArrayList<>();

            Coordinata coordinata81 = new Coordinata(13.3528519,42.3077842);
            coordinateConfini8.add(coordinata81);
            Coordinata coordinata82 = new Coordinata(13.402977,42.2701957);
            coordinateConfini8.add(coordinata82);
            Coordinata coordinata83 = new Coordinata(13.4352493,42.2384312);
            coordinateConfini8.add(coordinata83);
            Coordinata coordinata84 = new Coordinata(13.4634018,42.2625737);
            coordinateConfini8.add(coordinata84);
            Coordinata coordinata85 = new Coordinata(13.4457063,42.2909994);
            coordinateConfini8.add(coordinata85);
            Coordinata coordinata86 = new Coordinata(13.4671783,42.3080381);
            coordinateConfini8.add(coordinata86);
            Coordinata coordinata87 = new Coordinata(13.4884644,42.3133698);
            coordinateConfini8.add(coordinata87);
            Coordinata coordinata88 = new Coordinata(13.4836578,42.3242856);
            coordinateConfini8.add(coordinata88);
            Coordinata coordinata89 = new Coordinata(13.4256363,42.3258086);
            coordinateConfini8.add(coordinata89);
            Coordinata coordinata810 = new Coordinata(13.4026337,42.3054991);
            coordinateConfini8.add(coordinata810);
            Coordinata coordinata811 = new Coordinata(13.3528519,42.3077842); coordinateConfini8.add(coordinata811);

            zonaDiCaccia8.setCoordinateConfini(coordinateConfini8);

            //ZDC009
            ZonaDiCaccia zonaDiCaccia9 = new ZonaDiCaccia();

            zonaDiCaccia9.setId("ZDC009");

            zonaDiCaccia9.setNome("Area 9");

            Coordinata coordinataCentro9 = new Coordinata(13.4970474,42.4518347);
            zonaDiCaccia9.setCoordinataCentro(coordinataCentro9);

            ArrayList<Coordinata> coordinateConfini9 = new ArrayList<>();

            Coordinata coordinata91 = new Coordinata(13.4256363,42.3258086); coordinateConfini9.add(coordinata91);
            Coordinata coordinata92 = new Coordinata(13.4836578,42.3242856); coordinateConfini9.add(coordinata92);
            Coordinata coordinata93 = new Coordinata(13.4884644,42.3133698); coordinateConfini9.add(coordinata93);
            Coordinata coordinata94 = new Coordinata(13.5358429,42.3311388); coordinateConfini9.add(coordinata94);
            Coordinata coordinata95 = new Coordinata(13.5821915,42.3798508); coordinateConfini9.add(coordinata95);
            Coordinata coordinata96 = new Coordinata(13.6244202,42.4046996); coordinateConfini9.add(coordinata96);
            Coordinata coordinata97 = new Coordinata(13.6453629,42.4059672); coordinateConfini9.add(coordinata97);
            Coordinata coordinata98 = new Coordinata(13.6659622,42.4401809); coordinateConfini9.add(coordinata98);
            Coordinata coordinata99 = new Coordinata(13.6261368,42.4644993); coordinateConfini9.add(coordinata99);
            Coordinata coordinata910 = new Coordinata(13.5543823,42.4609535); coordinateConfini9.add(coordinata910);
            Coordinata coordinata911 = new Coordinata(13.5042572,42.4908336); coordinateConfini9.add(coordinata911);
            Coordinata coordinata912 = new Coordinata(13.4486389,42.5290493); coordinateConfini9.add(coordinata912);
            Coordinata coordinata913 = new Coordinata(13.4105301,42.536639); coordinateConfini9.add(coordinata913);
            Coordinata coordinata914 = new Coordinata(13.3779144,42.5209526); coordinateConfini9.add(coordinata914);
            Coordinata coordinata915 = new Coordinata(13.3325958,42.5001999); coordinateConfini9.add(coordinata915);
            Coordinata coordinata916 = new Coordinata(13.326416,42.4746292); coordinateConfini9.add(coordinata916);
            Coordinata coordinata917 = new Coordinata(13.3820343,42.4627264); coordinateConfini9.add(coordinata917);
            Coordinata coordinata918 = new Coordinata(13.3975241,42.4650344); coordinateConfini9.add(coordinata918);
            Coordinata coordinata919 = new Coordinata(13.4527588,42.4168669); coordinateConfini9.add(coordinata919);
            Coordinata coordinata920 = new Coordinata(13.4527588,42.3676763); coordinateConfini9.add(coordinata920);
            Coordinata coordinata921 = new Coordinata(13.4256363,42.3258086); coordinateConfini9.add(coordinata921);

            zonaDiCaccia9.setCoordinateConfini(coordinateConfini9);

            // ------------------------------------

            session.saveOrUpdate(zonaDiCaccia1);
            session.saveOrUpdate(zonaDiCaccia2);
            session.saveOrUpdate(zonaDiCaccia3);
            session.saveOrUpdate(zonaDiCaccia4);
            session.saveOrUpdate(zonaDiCaccia5);
            session.saveOrUpdate(zonaDiCaccia6);
            session.saveOrUpdate(zonaDiCaccia7);
            session.saveOrUpdate(zonaDiCaccia8);
            session.saveOrUpdate(zonaDiCaccia9);

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
