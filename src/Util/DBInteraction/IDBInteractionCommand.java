package Util.DBInteraction;

import java.util.List;

public interface IDBInteractionCommand {
    List<Object> execute (Object sessionObject);
}
