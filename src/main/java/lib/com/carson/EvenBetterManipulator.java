package lib.com.carson;


import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.List;

import static java.awt.Color.BLACK;

public class EvenBetterManipulator extends GenericManipulator{





    public static <T extends Enum<T>> void print(Database<T> db, Color c, List<T> t, String from, String to) throws IOException {
        run(c,from,to, (im,g2,color) -> {
            final int size = 10;
            for (int i = 0; i < t.size(); i++) {
                final Pair<Integer, Integer> cords = db.getCords(t.get(i));
                int x = cords.a;
                int y = cords.b;
                Rectangle2D rec = new Rectangle2D.Double(x - (size / 2), y - (size / 2), size, size);
                g2.fill(rec);
            }
        });
    }


    public static <T extends Enum<T>> void printAll(Database<T> db,Color c, String from, String to) throws IOException {
        run(c,from,to, (im,g2,color) -> {
            final int size = 10;
            for(T t : db.getEnumValues()){
                final Pair<Integer, Integer> cords = db.getCords(t);
                int x = cords.a;
                int y = cords.b;
                Rectangle2D rec = new Rectangle2D.Double(x-(size/2),y-(size/2),size,size);
                g2.fill(rec);
            }
        });
    }

    public static <T extends Enum<T>> void map(Database<T> db, Color c, List<T> t, String from, String to) throws IOException {
        run(c,from,to, ((im, g2, c1) -> {
            BasicStroke stroke = new BasicStroke(lineThickness);
            g2.setStroke(stroke);
            for(int i = 0;i<t.size()-1;i++){
                Pair<Integer,Integer> startCords = db.getCords(t.get(i));
                Pair<Integer,Integer> endCords = db.getCords(t.get(i+1));
                Color color = c;
                g2.setColor(color);
                g2.drawLine(startCords.a,startCords.b,endCords.a,endCords.b);
            }
        }));
    }


    public static <T extends Enum<T>> void mapAllConnections(Database<T> db, Color c, String from,String to) throws IOException {
        run(c,from,to, (im,g2,color) ->{
            BasicStroke stroke = new BasicStroke(lineThickness);
            g2.setStroke(stroke);
            for (T t1 : db.getEnumValues()) {
                Pair<Integer, Integer> stateCord1 = db.getCords(t1);
                for (T t2 : db.getConnections(t1)) {
                    Pair<Integer, Integer> stateCord2 = db.getCords(t2);
                    g2.drawLine(stateCord1.a, stateCord1.b, stateCord2.a, stateCord2.b);
                }
            }
        });
    }


    /**
     * is not properly tested...
     */
    public static <T extends Enum<T>> void addLabels(Database<T> db, String from, String to) throws IOException {
        run(BLACK,CACHE,CACHE,(im, g2, c) -> {
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            for(T t : db.getEnumValues()){
                Pair<Integer,Integer> cords = db.getAdjustedCords(t);
                g2.drawString(t + "",cords.a+10,cords.b-10);
            }
        });
    }



}
