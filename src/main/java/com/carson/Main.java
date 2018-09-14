package com.carson;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import static com.carson.BetterManipulator.*;
import static com.carson.BetterManipulator.CACHE;
import static com.carson.State.*;
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

//        Path path;
//        while(true) {
//            path = new Path(State.TX);
//            while (path.moveRandomly()) {
//                if (path.getLastState().equals(State.WA)) {
//                    break;
//                }
//            }
//            if (path.getLastState().equals(State.WA)) {
//                break;
//            }
//        }

        Path path = Path.fromList(WA,OR,CA);

        map(YELLOW,path.getStates(),CACHE,CACHE);
        print(GREEN,toList(path.getStartingState()),CACHE,CACHE);
        print(RED,toLine(path.getLastState()),CACHE,CACHE);
        copy(CACHE,"starting");

        java.util.List<Path> paths = path.branch();
        System.out.println("paths:" + paths.size());
        for(int i =0;i<paths.size();i++){
            Path working = paths.get(i);
            copy("../map",CACHE);
            mapAllConnections(BLACK,CACHE,CACHE);

            printAll(CYAN,CACHE,CACHE);

            print(BLACK,Database.done(),CACHE,CACHE);
            map(YELLOW,working.getStates(),CACHE,CACHE);
            print(GREEN,toList(working.getStartingState()),CACHE,CACHE);
            print(RED,toLine(working.getLastState()),CACHE,CACHE);
            copy(CACHE,"path-option:" + i);
        }


        copy(CACHE,"final");



    }





}
