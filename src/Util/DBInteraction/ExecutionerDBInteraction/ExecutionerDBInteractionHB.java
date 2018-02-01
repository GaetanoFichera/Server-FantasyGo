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
    private static ExecutionerDBInteractionHB singletonInstance = new ExecutionerDBInteractionHB();
    private Session session = null;

    private ExecutionerDBInteractionHB() {
    }

    public static ExecutionerDBInteractionHB getSingletonInstance(){
        return singletonInstance;
    }

    @Override
    public void openExecution() {
        if (this.session == null) this.session = HibernateUtil.getSession();
    }

    @Override
    public void closeExecution() {
        try{
            if (this.session != null){
                if (this.session.isOpen()) this.session.close();
                this.session = null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void roolbackExecution() {
        if (this.session.getTransaction().isActive()) this.session.getTransaction().rollback();
    }

    @Override
    public List<Object> executeCommand(IDBInteractionCommand command) {
        return command.execute(this.session);
    }
}
