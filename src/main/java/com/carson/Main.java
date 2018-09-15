package com.carson;

import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

import static com.carson.ImageManipulator.*;
import static java.awt.Color.*;

public class Main {

    private static final Scanner scan = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
        clearCache();
        Database.addAllConnections();

        copy("../map",CACHE);
        mapAllConnections(BLACK,CACHE,CACHE);
        printAll(CYAN,CACHE,CACHE);
        print(BLACK,Database.done(),CACHE,CACHE);
        print(RED,Database.connectedButNotDone(),CACHE,CACHE);

        ImageManipulator.run(BLACK,CACHE,CACHE,(im, g2, c) -> {
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 25));
           for(State state : State.values()){
               Pair<Integer,Integer> cords = Database.getAdjustedCords(state);
               g2.drawString(state + "",cords.a+10,cords.b-10);
           }
        });

        copy(CACHE,"final");


    }



}
