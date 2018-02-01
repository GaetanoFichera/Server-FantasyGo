package Util.DBInteraction.DBInteractionCommand;

import Util.DBInteraction.IDBInteractionCommand;

public abstract class SaveOrUpdateCommand implements IDBInteractionCommand{
    Class classPersistance;
    Object object;

    public void init(Class classPersistance, Object object) {
        this.classPersistance = classPersistance;
        this.object = object;
    }

    public Class getClassPersistance() {
        return classPersistance;
    }

    public void setClassPersistance(Class classPersistance) {
        this.classPersistance = classPersistance;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
