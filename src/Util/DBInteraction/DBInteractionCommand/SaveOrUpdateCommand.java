package Util.DBInteraction.DBInteractionCommand;

import Util.DBInteraction.IDBInteractionCommand;

public abstract class SaveOrUpdateCommand implements IDBInteractionCommand{
    Object object;

    public void init(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
