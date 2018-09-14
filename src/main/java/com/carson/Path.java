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
        old.add(_new);
        states = old;
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
}
