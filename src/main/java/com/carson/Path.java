package com.carson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Path {
    private List<State> states;
    public Path(State start){
        states = new ArrayList<>();
        states.add(start);
    }

    public Path(List<State> inStates){
        this(inStates.get((int)(Math.random()*inStates.size())));
    }

    public static Path fromList(List<State> states){
        Path p = new Path();
        p.states = states;
        return p;
    }
    public static Path fromList(State...states){
        return fromList(new ArrayList<>(Arrays.asList(states)));
    }

    public Path(List<State> old, State _new){
        states = new ArrayList<>(old);
        states.add(_new);
    }



    public Path(){
        this(new ArrayList<>(Arrays.asList(State.values())));
    }

    public List<State> getStates() {
        return states;
    }

    public void addState(State state){
        states.add(state);
    }

    public boolean moveRandomly(){
        State first = states.get(states.size() - 1);
        List<State> continued = Database.getConnections(first);
        for(State state : states){
            continued.remove(state);
        }
        if(continued.size() == 0){
            return false;
        }
        int random = (int) (Math.random() * continued.size());

        while(states.contains(continued.get(random))) {
            random = (int) (Math.random() * continued.size());
        }
        states.add(continued.get(random));
        return true;
    }


    public State getStartingState(){
        return states.get(0);
    }

    public State getLastState(){
        return states.get(states.size()-1);
    }


    public List<Path> branch(){
        State last = getLastState();
        List<State> continued = Database.getConnections(last);
        for(State state : states){
            continued.remove(state);
        }
        if(continued.size() == 0){
            return null;
        }
        List<Path> paths = new ArrayList<>();
        for(State state : continued){
            Path p = new Path(this.states,state);
            paths.add(p);
        }
        return paths;
    }



    public int size(){
        return states.size();
    }

    public int connections(){
        return states.size()-1;
    }

    public int distance(){
        int total = 0;
        for(int i = 0;i<states.size()-1;i++){
            int distance = Database.getConnection(states.get(i),states.get(i+1));
            total+=distance;
        }
        return total;
    }

    private boolean canGoSomewhere(){
        return !(branch() == null || branch().size() == 0);
    }

    private boolean isValid(State start, State end){
        return (getStartingState() == start && getLastState() == end);
    }


    public static Path goFrom(State start, State end){
        Path path = new Path(start);

        List<Path> valid = new ArrayList<>();
        List<Path> nextBranch = path.branch();
        List<Path> branched;

        while(nextBranch.size() > 0) {
            branched = nextBranch;
            nextBranch = new ArrayList<>();

            while (branched.size() > 0) {
                Path current = branched.get(0);
                if (current.isValid(start, end)) {
//                    System.out.println("found valid path!");
                    valid.add(current);
                    branched.remove(current);
                    continue;
                }
//                System.out.println(current.canGoSomewhere());
//                System.out.println(current.isValid(start, end));
                if (!current.canGoSomewhere() && !current.isValid(start, end)) {
//                    System.out.println("can't be continued");
                    branched.remove(current);
                } else {
                    //can be continued
//                    System.out.println("can be continued");

                    nextBranch.addAll(current.branch());
                    branched.remove(current);
                }

            }

        }


        //filter paths
        int shortest = -1;
        Path shortestPath = valid.get(0);
        for(Path p : valid){
            int dis = p.distance();
            if(dis < shortest){
                shortest = dis;
                shortestPath = p;
            }
        }

        return shortestPath;


    }


    @Override
    public String toString() {
        String str = "PATH:";
        for(State s : states){
            str+=s + "->";
        }
        str = str.substring(0,str.length()-2);
        return str;
    }
}
