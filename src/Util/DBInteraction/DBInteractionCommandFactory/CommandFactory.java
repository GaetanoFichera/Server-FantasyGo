package Util.DBInteraction.DBInteractionCommandFactory;

import Util.DBInteraction.IDBInteractionCommand;

public  class CommandFactory {
    public final static String HIBERNATE = "HB";
    public final static String DELETE = "DeleteCommand";
    public final static String FINDALL = "FindAllCommand";
    public final static String FINDBYID = "FindByIdCommand";
    public final static String FINDBYVALUE = "FindByValueCommand";
    public final static String SAVEORUPDATE = "SaveOrUpdateCommand";

    public static IDBInteractionCommand getCommandInstance(String DBInteractionCommand,String DBInteractionType) {
        IDBInteractionCommand command = null;
        try {
            command = (IDBInteractionCommand) Class.forName("Util.DBInteraction.DBInteractionCommand"+ DBInteractionType+"."+ DBInteractionCommand + DBInteractionType).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return command;
    }
}
