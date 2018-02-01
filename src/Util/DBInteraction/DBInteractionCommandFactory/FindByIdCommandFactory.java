package Util.DBInteraction.DBInteractionCommandFactory;

import Util.DBInteraction.IDBInteractionCommand;

public class FindByIdCommandFactory extends CommandFactory{
    @Override
    public IDBInteractionCommand getCommandInstance(String DBInteractionType) {
        IDBInteractionCommand command = null;
        try {
            command = (IDBInteractionCommand) Class.forName("Util.DBInteraction.DBInteractionCommand" + DBInteractionType + ".FindByIdCommand" + DBInteractionType).newInstance();
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
