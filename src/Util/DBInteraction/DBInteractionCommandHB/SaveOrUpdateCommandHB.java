package Util.DBInteraction.DBInteractionCommandHB;

import Util.DBInteraction.DBInteractionCommand.SaveOrUpdateCommand;
import org.hibernate.Session;

import java.util.List;

public class SaveOrUpdateCommandHB extends SaveOrUpdateCommand{

    @Override
    public List<Object> execute(Object sessionObject) {
        Session session = ((Session) sessionObject);
        session.saveOrUpdate(getObject());
        session.getTransaction().commit();

        return null;
    }
}
