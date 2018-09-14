package com.carson;

import java.util.*;

import static com.carson.State.*;

public class Database {
    private static Map<State,Integer> states = new HashMap<>();
    private static Map<State, Pair<Integer,Integer>> stateCords = new HashMap<>();
    private static Map<Integer,Integer> allConnections = new HashMap<>();
    private static Map<Integer,Integer> connections = new HashMap<>();//the working connections
    static{
        initializeStates();
        initializeConnections();
    }

    private static void initializeStates(){
        int next = 2;
        for(State state : State.values()){
            while(!isPrime(++next)){}
            states.put(state,next);
        }
        initializeCords();
    }

    private static void initializeCords() {
        stateCords.put(State.AL, new Pair<>(1292, 842));
        stateCords.put(State.AK, new Pair<>(57, 902));
        stateCords.put(State.AZ, new Pair<>(363, 749));
        stateCords.put(State.AR, new Pair<>(1074, 779));
        stateCords.put(State.CA, new Pair<>(105, 575));
        stateCords.put(State.CO, new Pair<>(608, 570));
        stateCords.put(State.CT, new Pair<>(1722, 375));
        stateCords.put(State.DE, new Pair<>(1659, 523));
        stateCords.put(State.DC, new Pair<>(1590, 535));//1597, 529
        stateCords.put(State.FL, new Pair<>(1499, 988));
        stateCords.put(State.GA, new Pair<>(1423, 845));
        stateCords.put(State.HI, new Pair<>(87, 1071));
        stateCords.put(State.ID, new Pair<>(353, 316));
        stateCords.put(State.IL, new Pair<>(1182, 535));
        stateCords.put(State.IN, new Pair<>(1285, 524));
        stateCords.put(State.IA, new Pair<>(1033, 442));
        stateCords.put(State.KS, new Pair<>(861, 609));
        stateCords.put(State.KY, new Pair<>(1339, 632));
        stateCords.put(State.LA, new Pair<>(1082, 940));
        stateCords.put(State.ME, new Pair<>(1789, 185));
        stateCords.put(State.MD, new Pair<>(1605, 506));
        stateCords.put(State.MA, new Pair<>(1743, 339));
        stateCords.put(State.MI, new Pair<>(1318, 374));
        stateCords.put(State.MN, new Pair<>(996, 276));
        stateCords.put(State.MS, new Pair<>(1189, 865));
        stateCords.put(State.MO,new Pair<>(1070,622));
        stateCords.put(State.MT,new Pair<>(536,189));
        stateCords.put(State.NE,new Pair<>(823,467));
        stateCords.put(State.NV,new Pair<>(239,494));
        stateCords.put(State.NH,new Pair<>(1733,282));
        stateCords.put(State.NJ,new Pair<>(1680,456));
        stateCords.put(State.NM,new Pair<>(578,774));
        stateCords.put(State.NY,new Pair<>(1631,345));
        stateCords.put(State.NC,new Pair<>(1564,686));
        stateCords.put(State.ND,new Pair<>(814,206));
        stateCords.put(State.OH,new Pair<>(1398,508));
        stateCords.put(State.OK,new Pair<>(905,750));
        stateCords.put(State.OR,new Pair<>(169,268));
        stateCords.put(State.PA,new Pair<>(1567,448));
        stateCords.put(State.RI,new Pair<>(1759,365));
        stateCords.put(State.SC,new Pair<>(1518,776));
        stateCords.put(State.SD,new Pair<>(806,334));
        stateCords.put(State.TN,new Pair<>(1301,712));
        stateCords.put(State.TX,new Pair<>(849,944));
        stateCords.put(State.UT,new Pair<>(409,545));
        stateCords.put(State.VT,new Pair<>(1695,268));
        stateCords.put(State.VA,new Pair<>(1584,591));
        stateCords.put(State.WA,new Pair<>(213,118));
        stateCords.put(State.VV,new Pair<>(1480,568));
        stateCords.put(State.WI,new Pair<>(1144,332));
        stateCords.put(State.WY,new Pair<>(579,380));

        //tweaks
        for(State state : stateCords.keySet()){
            Pair<Integer,Integer> cords = stateCords.get(state);
            cords = new Pair<>(cords.a,cords.b-30);
            stateCords.replace(state,cords);
        }


    }


    //done states
    public static List<State> done(){
        return Arrays.asList(WA,OR,ID,CA,NV,MT,WY,UT,AZ,ND,SD,NM,CO,OK);
    }

    private static void initializeConnections(){
        //all border connections between states

        //WA
        addConnection(WA,OR,273);
        addConnection(WA,ID,384);

        //OR
        addConnection(OR,ID,290);
        addConnection(OR,CA,489);
        addConnection(OR,NV,407);

        //ID
        addConnection(ID,MT,287);
        addConnection(ID,WY,379);
        addConnection(ID,UT,378);
        addConnection(ID,NV,374);

        //CA
        addConnection(CA,NV,215);
        addConnection(CA,AZ,505);

        //NV
        addConnection(NV,UT,288);
        addConnection(NV,AZ,442);

        //MT
        addConnection(MT,WY,302);
        addConnection(MT,ND,441);
        addConnection(MT,SD,545);


        //WY
        addConnection(WY,UT,326);
        addConnection(WY,NE,393);
        addConnection(WY,CO,256);
        addConnection(WY,SD, 375);


        //UT
        addConnection(UT,AZ,364);
        addConnection(UT,CO,284);
        addConnection(UT,NM, 439);

        //AZ
        addConnection(AZ,NM,300);


        //ND
        addConnection(ND,SD,253);
        addConnection(ND,MN,302);

        //SD
        addConnection(SD,MN,317);
        addConnection(SD,IA,373);
        addConnection(SD,NE,171);

        //NM
        addConnection(NM,TX,387);
        addConnection(NM,OK,579);
        addConnection(NM,CO,347);

        //CO
        addConnection(CO,OK,569);
        addConnection(CO,NE,337);
        addConnection(CO,KS,392);


        //OK
        addConnection(OK,TX,400);
        addConnection(OK,AR,253);
        addConnection(OK,KS,209);
        addConnection(OK,MO,240);

    }

    private static void addConnection(State a, State b,int distance){
        allConnections.put( genPrime(a,b),distance);
    }






    private static boolean isPrime(int n) {
        if (n%2==0) return false;
        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }


    public static void addConnection(State a, State b){
        int prime = genPrime(a,b);
        connections.put(prime,allConnections.get(prime));
    }

    public static Pair<Integer,Integer> getCords(State s){
        return stateCords.get(s);
    }

    private static int genPrime(State a, State b) {
        return states.get(a)*states.get(b);
    }


    public static int getConnection(State a, State b) {
        int prime = genPrime(a,b);
        if(connections.containsKey(prime)){
            return connections.get(prime);
        }
        return -1;
    }

    public static void addAllConnections() {
        connections = allConnections;
    }


    public static Map<Integer, Integer> getConnections() {
        return connections;
    }

    public static int getId(State state) {
        return states.get(state);
    }

    public static Map<State, Integer> getStates() {
        return states;
    }

    public static State getStateById(int id) {
        for(State state : states.keySet()){
            if(states.get(state) == id)return state;
        }
        return null;
    }


    public static List<State> getConnections(State state){
        List<State> states = new ArrayList<>();
        double primeId = getId(state);//double so that the division is double division
        for(Integer primeTest : connections.keySet()){
            if(primeTest / primeId % 1 == 0){
                State s = getStateById((int)(primeTest/primeId));
                if(s == null){
                    System.err.println("error");
                }
                states.add(s);
            }
        }
        return states;
    }
}