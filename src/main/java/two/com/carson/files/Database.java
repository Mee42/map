package two.com.carson.files;



import java.util.*;


public abstract class Database<T extends Enum<T>> {
    protected final Map<T,Integer> nodes = new HashMap<>();
    protected final Map<T, Pair<Integer,Integer>> cords = new HashMap<>();
    protected final Map<T, Pair<Integer,Integer>> cordAdjustments = new HashMap<>();
    protected final Map<Integer,Integer> connections = new HashMap<>();//the working connections


    protected final Class<T> enumClass;

    public Database(Class<T> enumClass){
        this.enumClass = enumClass;
        initializeNodes();
        initializeCords();
        initializeConnections();
        initializeAdjustments();
    }


    public void initializeNodes(){
        int next = 2;
        for(T t : enumClass.getEnumConstants()){
            while(!isPrime(++next)){}
            nodes.put(t,next);
        }
    }

    public abstract void initializeCords();

    public abstract void initializeConnections();

    public abstract void initializeAdjustments();



    protected void adjust(T t, int x, int y){
        cordAdjustments.put(t,new Pair<>(x,y));
    }


    public List<T> getAllNodesWithConnections(){
        List<T> nodes = new ArrayList<>(Arrays.asList(enumClass.getEnumConstants()));
        for(int i = 0;i< nodes.size();i++){
            if(getConnections(nodes.get(i)).size() == 0){
                nodes.remove(i);
                i--;
            }
        }
        return nodes;
    }



    protected void addConnection(T a, T b,int distance){
        connections.put( genPrime(a,b),distance);
    }


    public boolean isConnectedToMap(T s){
        boolean connected = getAllNodesWithConnections().contains(s);
        return connected;
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


    public void addConnection(T a, T b){
        int prime = genPrime(a,b);
        connections.put(prime,connections.get(prime));
    }

    public Pair<Integer,Integer> getCords(T s){
        return cords.get(s);
    }

    public Pair<Integer,Integer> getAdjustedCords(T s){
        if(!cordAdjustments.containsKey(s)){
            return getCords(s);
        }
        Pair<Integer,Integer> cords = getCords(s);
        Pair<Integer,Integer> adj = cordAdjustments.get(s);
        Pair<Integer,Integer> result = new Pair<>(cords.a+adj.a,cords.b+adj.b);
        return result;
    }


    private int genPrime(T a, T b) {
        return nodes.get(a)* nodes.get(b);
    }


    public int getConnection(T a, T b) {
        int prime = genPrime(a,b);
        if(connections.containsKey(prime)){
            return connections.get(prime);
        }
        return -1;
    }


    public Map<Integer, Integer> getConnections() {
        return connections;
    }

    public int getId(T state) {
        return nodes.get(state);
    }

    public Map<T, Integer> getStates() {
        return nodes;
    }

    public T getNodeById(int id) {
        for(T node : nodes.keySet()){
            if(nodes.get(node) == id)return node;
        }
        return null;
    }


    public List<T> getConnections(T t){
        List<T> nodes = new ArrayList<>();
        double primeId = getId(t);//double so that the division is double division
        for(Integer primeTest : connections.keySet()){
            if(primeTest / primeId % 1 == 0){
                T s = getNodeById((int)(primeTest/primeId));
                if(s == null){
                    System.err.println("error");
                }
                nodes.add(s);
            }
        }
        return nodes;
    }
}
