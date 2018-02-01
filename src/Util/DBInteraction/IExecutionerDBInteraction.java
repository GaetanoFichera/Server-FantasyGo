package Util.DBInteraction;

import java.util.List;

public interface IExecutionerDBInteraction {
    static IExecutionerDBInteraction getSingletonInstance() {
        return null;
    }

    void openExecution();
    void closeExecution();
    void roolbackExecution();
    List<Object> executeCommand(IDBInteractionCommand command);
}
