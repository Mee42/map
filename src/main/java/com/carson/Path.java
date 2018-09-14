package com.carson;

import java.util.ArrayList;
import java.util.List;

public class Path {
    public final List<State> states = new ArrayList<>();

    public Path(State start){
        states.add(start);
    }
    public Path(List<State> inStates){
        this(inStates.get((int)(Math.random()*inStates.size())));
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




    public int size(){
        return states.size();
    }
}
