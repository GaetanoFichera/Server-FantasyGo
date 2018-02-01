package Util.DBInteraction.ExecutionerDBInteraction;

import Util.DBInteraction.IDBInteractionCommand;
import Util.DBInteraction.IExecutionerDBInteraction;
import Util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@PersistenceContext(unitName="bigbrother", type= PersistenceContextType.EXTENDED)
public class ExecutionerDBInteractionHB implements IExecutionerDBInteraction{
    private Session session = null;

    public ExecutionerDBInteractionHB() {
    }

    @Override
    public void openExecution() {
        if (this.session == null) this.session = HibernateUtil.getSession();
    }

    @Override
    public void closeExecution() {
        System.out.println("Sessione da chiudere " + this.session);
        if (this.session != null) this.session.close();
    }

    @Override
    public void roolbackExecution() {
        if (this.session.getTransaction().isActive()) this.session.getTransaction().rollback();
    }

    @Override
    public List<Object> executeCommand(IDBInteractionCommand command) {
        System.out.println("ExecuteCommand");
        System.out.println("Passo la sessione al command: " + this.session);
        return command.execute(this.session);
    }
}
