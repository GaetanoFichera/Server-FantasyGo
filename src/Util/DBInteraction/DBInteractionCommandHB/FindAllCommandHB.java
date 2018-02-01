package Util.DBInteraction.DBInteractionCommandHB;

import Entity.ZonaDiCaccia;
import Util.DBInteraction.DBInteractionCommand.FindAllCommand;
import org.hibernate.Session;

import java.util.List;

public class FindAllCommandHB extends FindAllCommand {
    @Override
    public List<Object> execute(Object sessionObject) {
        Session session = (Session) sessionObject;
        List<Object> allObject = session.createCriteria(ZonaDiCaccia.class).list();

        return allObject;
    }
}
