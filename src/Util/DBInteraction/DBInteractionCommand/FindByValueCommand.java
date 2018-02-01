package Util.DBInteraction.DBInteractionCommand;

import Util.DBInteraction.IDBInteractionCommand;

import java.io.Serializable;

public abstract class FindByValueCommand implements IDBInteractionCommand{
    private Serializable parameter;
    private Serializable value;
    private Class classPersistance;

    public void init(Serializable parameter, Serializable value, Class classPersistance) {
        this.parameter = parameter;
        this.value = value;
        this.classPersistance = classPersistance;
    }

    public Serializable getParameter() {
        return parameter;
    }

    public void setParameter(Serializable parameter) {
        this.parameter = parameter;
    }

    public Serializable getValue() {
        return value;
    }

    public void setValue(Serializable value) {
        this.value = value;
    }

    public Class getClassPersistance() {
        return classPersistance;
    }

    public void setClassPersistance(Class classPersistance) {
        this.classPersistance = classPersistance;
    }
}
