import lib.com.carson.Database;
import lib.com.carson.Pair;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static two.com.carson.files.TEST.TestValues.*;

public class TEST {
    public enum TestValues{
        A,B,C,D,E
    }
    public static void main(String[] args) throws IOException {

    }


    @Test
    private static void test(){
        Database<TestValues> db = new Database<TestValues>(TestValues.class){
            @Override
            public void initializeCords() {
                cords.put(A,new Pair<>(1,1));
                cords.put(B,new Pair<>(1,10));
                cords.put(C,new Pair<>(10,10));
                cords.put(D,new Pair<>(10,1));
                cords.put(E,new Pair<>(5,5));
                /*
                 *  A     B
                 *
                 *  C     D
                 */
            }

            @Override
            public void initializeConnections() {
                addConnection(A,B,5);
                addConnection(A,C,5);
                addConnection(D,C,5);
                addConnection(D,B,5);
            }

            @Override
            public void initializeAdjustments() {
                //none
            }
        };

        //tests


        String str = "";
        for(TestValues t : db.getAllNodesWithConnections()){ str+=t; }
        assertEquals(str,"ABCD");

        assertTrue(db.isConnectedToMap(A));
        assertTrue(!db.isConnectedToMap(E));

        assertEquals(db.getCords(A), new Pair<>(1,1));
        assertEquals(db.getCords(B), new Pair<>(1,10));
        assertEquals(db.getCords(C), new Pair<>(10,10));
        assertEquals(db.getCords(D), new Pair<>(10,1));

        assertEquals(db.getConnection(A,B),5);
        assertEquals(db.getConnection(A,E),-1);

        assertEquals(db.getCords(E).toString(),"5,5");


        assertTrue(db.getConnections(A).contains(B));
        assertTrue(!db.getConnections(A).contains(E));

    }


}
