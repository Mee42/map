package com.carson;

import java.io.IOException;
import java.util.Scanner;

import static com.carson.ImageManipulator.*;
import static java.awt.Color.*;

public class Mapper {

    private static final Scanner scan = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
        clearCache();
        Database.addAllConnections();

        int index = 0;
        while(true) {
            System.out.print("first state:");
            State start = readState();
            System.out.print("ending state:");
            State end = readState();
            if(end.equals(start)){
                System.err.println("can not connect the same state");
                continue;
            }
            if (!Database.isConnectedToMap(start)) {
                System.out.println(start + " is currently unavailable");
                return;
            }
            if (!Database.isConnectedToMap(end)) {
                System.out.println(end + " is currently unavailable");
                return;
            }

            Path quickest = Path.goFrom(start, end);
            System.out.println("path found:" + quickest);
            System.out.println("the distance is " + quickest.distance() + " miles");
            System.out.println("mapping to final_" + index);
            normalMap(quickest, "final_" + index);
            index++;
        }
    }

    private static State readState(){
        while(true) {
            String in = scan.nextLine();
            switch (in.toUpperCase()) {
                case "STATES":
                case "GET":
                    String str = "States currently available:";
                    for (State s : Database.getAllConnectedStates()) {
                        str += s + " ";
                    }
                    System.out.println(str);
                    System.out.print("please enter a state:");
                    continue;
                case "HELP":
                    System.out.println("type two state codes in. use STATES to get available states");
                    System.out.print("please enter a state:");
                    continue;
                default:
                    try {
                        State s = State.valueOf(in.toUpperCase());
                        if(!Database.isConnectedToMap(s)){
                            System.err.print("state is not available");
                        }else{
                            return s;
                        }
                    }catch (IllegalArgumentException e){
                        System.err.print("invalid state code ");
                    }

            }
            System.err.print("  error. try again:");
        }
    }



    public static void normalMap(Path path, String to) throws IOException {
        copy("../map",CACHE);
        mapAllConnections(BLACK,CACHE,CACHE);
        map(YELLOW, path.getStates(), CACHE, CACHE);
        printAll(CYAN,CACHE,CACHE);
        print(BLACK,Database.done(),CACHE,CACHE);
        print(GREEN, toList(path.getStartingState()), CACHE, CACHE);
        print(RED, toLine(path.getLastState()), CACHE, CACHE);
        copy(CACHE, to);
    }

}
