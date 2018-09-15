package lib.com.carson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Path<T extends Enum<T>> {

    private List<T> nodes;
    private final Database<T> db;

    private Path(Database<T> db){
        this.db = db;
    }
    public Path(Database<T> db, T start){
        this.db = db;
        nodes = new ArrayList<>();
        nodes.add(start);
    }

    public Path(Database<T> db,List<T> in) {
        this(db,in.get((int)(Math.random()*in.size())));
    }

    public static <T extends Enum<T>> Path fromList(Database<T> db,List<T> ts){
        Path p = new Path(db);
        p.nodes = ts;
        return p;
    }
    public static <T extends Enum<T>> Path fromList(Database<T> db,T...nodes){
        return fromList(db,new ArrayList<>(Arrays.asList(nodes)));
    }

    public Path(Database<T> db,List<T> old, T _new){
        this.db = db;
        nodes = new ArrayList<>(old);
        nodes.add(_new);
    }



    public Path(Database<T> db, Class<T> enumClass){
//        this(new ArrayList<>(DatabaseManager.get(enumClass).getEnumValues()));
        this(db, Arrays.asList(enumClass.getEnumConstants()));
    }

    public List<T> getNodes() {
        return nodes;
    }

    public void addNode(T node){
        nodes.add(node);
    }

    public boolean moveRandomly(){
        T first = nodes.get(nodes.size() - 1);
        List<T> continued = db.getConnections(first);
        for(T node : nodes){
            continued.remove(node);
        }
        if(continued.size() == 0){
            return false;
        }
        int random = (int) (Math.random() * continued.size());

        while(nodes.contains(continued.get(random))) {
            random = (int) (Math.random() * continued.size());
        }
        nodes.add(continued.get(random));
        return true;
    }


    public T getStartingNode(){
        return nodes.get(0);
    }

    public List<T> getStartingNodeList(){
        return Arrays.asList(getStartingNode());
    }

    public T getLastNode(){
        return nodes.get(nodes.size()-1);
    }

    public List<T> getLastNodeList(){
        return Arrays.asList(getLastNode());
    }

    public List<Path> branch(){
        T last = getLastNode();
        List<T> continued = db.getConnections(last);
        for(T state : nodes){
            continued.remove(state);
        }
        if(continued.size() == 0){
            return null;
        }
        List<Path> paths = new ArrayList<>();
        for(T state : continued){
            Path p = new Path(db,this.nodes,state);
            paths.add(p);
        }
        return paths;
    }



    public int size(){
        return nodes.size();
    }

    public int connections(){
        return nodes.size()-1;
    }

    public int distance(){
        int total = 0;
        for(int i = 0; i< nodes.size()-1; i++){
            int distance = db.getConnection(nodes.get(i), nodes.get(i+1));
            total+=distance;
        }
        return total;
    }

    private boolean canGoSomewhere(){
        return !(branch() == null || branch().size() == 0);
    }

    private boolean isValid(T start, T end){
        return (getStartingNode() == start && getLastNode() == end);
    }


    public static <T extends Enum<T>> Path goFrom(Database<T> db, T start, T end){

        Path path = new Path(db,start);

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
        for(T s : nodes){
            str+=s + "->";
        }
        str = str.substring(0,str.length()-2);
        return str;
    }

}
