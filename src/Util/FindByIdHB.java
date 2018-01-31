package Util;

import org.hibernate.Session;

import java.util.List;

public class FindByIdHB extends FindByIdCommand {

    public FindByIdHB(List<Object> objects) {
        super(objects);
    }

    @Override
    public void execute(Object Object) {
        super.execute(Object);
        //implementa la sua execute specializzata
    }
}
