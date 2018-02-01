package Util.DBInteraction.DBInteractionCommand;

import Util.DBInteraction.IDBInteractionCommand;

import java.io.Serializable;

public abstract class DeleteCommand implements IDBInteractionCommand{
    private Serializable id;
    private Class classPersistance;

    public void init(Serializable id, Class classPersistance) {
        this.id = id;
        this.classPersistance = classPersistance;
    }

    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

    public Class getClassPersistance() {
        return classPersistance;
    }

    public void setClassPersistance(Class classPersistance) {
        this.classPersistance = classPersistance;
    }
}
