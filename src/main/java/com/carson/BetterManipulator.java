package com.carson;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

public class BetterManipulator {
    private static final int lineThickness = 3;
    public static void clearCache(){
        for(File f : new File("img/cache/").listFiles()){
            f.delete();
        }
    }

    interface I{
        void run(BufferedImage im, Graphics2D g2, Color c);
    }

    private static BufferedImage cacheIm;
    private static Graphics2D cacheG2;


    public static final String CACHE = "cache";

    private static void run(Color c,String from,String to, I i) throws IOException {
        BufferedImage im;
        Graphics2D g2;

        if(!from.equals(CACHE)) {
            im = ImageIO.read(getFile(from));
            g2 = im.createGraphics();
        }else{
            im = cacheIm;
            g2 = cacheG2;
        }
        g2.setColor(c);
        i.run(im,g2,c);
        if(!to.equals(CACHE)) {
            ImageIO.write(im, "png", getFile(to));
        }else{
            cacheIm = im;
            cacheG2 = g2;
        }

    }

    public static void copy(String from, String to) throws IOException {
        if(from.equals(CACHE) || to.equals(CACHE))
            run(Color.BLACK,from,to,(c,im,g2) -> {});
        else
            Files.copy(getFile(from).toPath(),getFile(to).toPath());
    }
    public static void copyForce(String from,String to) throws IOException{
        if(getFile(to).exists())
            getFile(to).delete();
        copy(from,to);
    }


    public static void print(Color c, java.util.List<State> states, String from, String to) throws IOException {
        run(c,from,to, (im,g2,color) -> {
            final int size = 10;
            for (int i = 0; i < states.size(); i++) {
                final Pair<Integer, Integer> cords = Database.getCords(states.get(i));
                int x = cords.a;
                int y = cords.b;
                Rectangle2D rec = new Rectangle2D.Double(x - (size / 2), y - (size / 2), size, size);
                g2.fill(rec);
            }
        });
    }


    public static void printAll(Color c, String from, String to) throws IOException {
        run(c,from,to, (im,g2,color) -> {
            final int size = 10;
            for(State state : State.values()){
                final Pair<Integer, Integer> cords = Database.getCords(state);
                int x = cords.a;
                int y = cords.b;
                Rectangle2D rec = new Rectangle2D.Double(x-(size/2),y-(size/2),size,size);
                g2.fill(rec);
            }
        });
    }

    public static void map(Color c, java.util.List<State> states, String from, String to) throws IOException {
        run(c,from,to, ((im, g2, c1) -> {
            BasicStroke stroke = new BasicStroke(lineThickness);
            g2.setStroke(stroke);
            for(int i = 0;i<states.size()-1;i++){
                Pair<Integer,Integer> startCords = Database.getCords(states.get(i));
                Pair<Integer,Integer> endCords = Database.getCords(states.get(i+1));
                Color color = c;
                g2.setColor(color);
                g2.drawLine(startCords.a,startCords.b,endCords.a,endCords.b);
            }
        }));
    }


    public static void mapAllConnections(Color c, String from,String to) throws IOException {
        run(c,from,to, (im,g2,color) ->{
            BasicStroke stroke = new BasicStroke(lineThickness);
            g2.setStroke(stroke);
            for (State state1 : State.values()) {
                Pair<Integer, Integer> stateCord1 = Database.getCords(state1);
                for (State state2 : Database.getConnections(state1)) {
                    Pair<Integer, Integer> stateCord2 = Database.getCords(state2);
                    g2.drawLine(stateCord1.a, stateCord1.b, stateCord2.a, stateCord2.b);
                }
            }
        });
    }



    public static File getFile(String str){
        return new File("img/cache/" + str + ".png");
    }



    public static java.util.List<State> toList(State s){
        return new ArrayList<>(Arrays.asList(s));
    }

    public static java.util.List<State> toLine(State...s){
        return new ArrayList<>(Arrays.asList(s));
    }


}
