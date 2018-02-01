package Util.DBInteraction.DBInteractionCommandFactory;

import Util.DBInteraction.IDBInteractionCommand;

public abstract class CommandFactory {
    public final static String HIBERNATE = "HB";

    public IDBInteractionCommand getCommandInstance(String DBInteractionType) {
        return null;
    }
}
