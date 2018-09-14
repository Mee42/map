package com.carson;

import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

import static com.carson.BetterManipulator.*;
import static com.carson.BetterManipulator.CACHE;
import static java.awt.Color.*;

public class Main {

    private static final Scanner scan = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
        clearCache();
        Database.addAllConnections();

        copy("../map",CACHE);
            mapAllConnections(BLACK,CACHE,CACHE);

        printAll(RED,CACHE,CACHE);

        print(BLACK,Database.done(),CACHE,CACHE);


        copy(CACHE,"final");



    }


}
