package Util.DBInteraction.DBInteractionCommand;

import Util.DBInteraction.IDBInteractionCommand;

public abstract class FindAllCommand implements IDBInteractionCommand {
    private Class persistenceClass;

    public void init(Class persistenceClass){
        this.persistenceClass = persistenceClass;
    }
}
