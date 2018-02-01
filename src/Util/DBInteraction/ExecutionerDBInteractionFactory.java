package Util.DBInteraction;

import Util.DBInteraction.ExecutionerDBInteraction.ExecutionerDBInteractionHB;

public class ExecutionerDBInteractionFactory {
    public final static String HIBERNATE = "HB";

    public IExecutionerDBInteraction getExecutionerDBIntectionInstance(String execDBIntType){
        IExecutionerDBInteraction executionerDBInteraction = null;
        if(execDBIntType == HIBERNATE){
            return ExecutionerDBInteractionHB.getSingletonInstance();
        }

        return executionerDBInteraction;
    }
}
