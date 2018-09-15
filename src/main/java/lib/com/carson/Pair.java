package lib.com.carson;

public class Pair<A,B> {
    public final A a;
    public final B b;

    public Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return a + "," + b;
    }

    @Override
    public int hashCode() {
        return a.hashCode() * b.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(!o.getClass().equals(this.getClass())){
            return false;
        }
        Pair other = (Pair<Integer,Integer>)o;
        return other.a.equals(this.a) && other.b.equals(this.b);
    }
}
