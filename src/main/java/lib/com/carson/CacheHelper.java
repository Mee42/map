package lib.com.carson;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import static lib.com.carson.AdvancedManipulator.CACHE;
/**
 * the final wrapper for image manipulators. Adds overloaded methods to avoid redundency
 */
public interface CacheHelper {


    static <T extends Enum<T>> void print(Database<T> db, Color c, List<T> t) throws IOException {
        AdvancedManipulator.print(db,c,t,CACHE,CACHE);
    }

    static <T extends Enum<T>> void printAll(Database<T> db,Color c) throws IOException {
        AdvancedManipulator.printAll(db,c,CACHE,CACHE);
    }

    static <T extends Enum<T>> void map(Database<T> db, Color c, List<T> t) throws IOException {
        AdvancedManipulator.map(db,c,t,CACHE,CACHE);
    }

    static <T extends Enum<T>> void mapAllConnections(Database<T> db, Color c) throws IOException {
        AdvancedManipulator.mapAllConnections(db,c,CACHE,CACHE);
    }

    static <T extends Enum<T>> void addLabels(Database<T> db) throws IOException {
        AdvancedManipulator.addLabels(db,CACHE,CACHE);
    }


}
