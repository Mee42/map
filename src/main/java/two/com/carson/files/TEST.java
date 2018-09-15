package two.com.carson.files;
import static two.com.carson.files.TEST.TestValues.*;

import org.junit

public class TEST {
    public enum TestValues{
        A,B,C,D,E
    }
    public static void main(String[] args) {

        Database<TestValues> db = new Database<TestValues>(TestValues.class){
            @Override
            public void initializeCords() {
                cords.put(A,new Pair<>(1,1));
                cords.put(B,new Pair<>(1,10));
                cords.put(C,new Pair<>(10,10));
                cords.put(D,new Pair<>(10,1));
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
        for(TestValues t : db.getAllNodesWithConnections()){
            str+=t;
        }
        System.out.print("db.getAllNodesWithConnections:" + str);




        System.out.println("db.isConnectedToMap(A): " + db.isConnectedToMap(A));

        System.out.println("db.isConnectedToMap(E): " + db.isConnectedToMap(E));


    }





}
