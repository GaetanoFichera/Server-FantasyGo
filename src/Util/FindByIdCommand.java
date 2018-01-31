package Util;

import org.apache.commons.lang.SerializationUtils;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public abstract class FindByIdCommand implements ICommand{
    Serializable id;
    Serializable nomeTabella;

    public FindByIdCommand(List<Object> objects){
        //da implementare init attributi
    }

    @Override
    public void execute(Object Object) {

    }
}
