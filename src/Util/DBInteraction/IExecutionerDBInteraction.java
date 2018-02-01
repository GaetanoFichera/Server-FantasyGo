package Util.DBInteraction;

import java.util.List;

public interface IExecutionerDBInteraction {
    void openExecution();
    void closeExecution();
    void roolbackExecution();
    List<Object> executeCommand(IDBInteractionCommand command);
}
