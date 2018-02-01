package Util.DBInteraction;

import Util.DBInteraction.ExecutionerDBInteraction.ExecutionerDBInteractionHB;

public class ExecutionerDBInteractionFactory {
    public final static String HIBERNATE = "HB";

    public IExecutionerDBInteraction getExecutionerDBIntectionInstance(String execDBIntType){
        IExecutionerDBInteraction executionerDBInteraction = null;
        if(execDBIntType == HIBERNATE){
            System.out.println("Sono nell'if della factory");
            return ExecutionerDBInteractionHB.getSingletonInstance();
        }
        /*
        try {

             executionerDBInteraction = ((IExecutionerDBInteraction) Class.forName("Util.DBInteraction.ExecutionerDBInteraction.ExecutionerDBInterface" + ExecDBIntType).newInstance()).getSingletonInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        return executionerDBInteraction;
    }
}
