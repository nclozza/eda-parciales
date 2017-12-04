package SP_2C_2009;

public class Main {
    public static void main(String[] args){
        Graph<String> g = new Graph<>();
        g.addVertex("A",0 );
        g.addVertex("B",0 );
        g.addVertex("C",0 );
        g.addVertex("D",0 );
        g.addArc("A", "B", 0);
        g.addArc("A", "C", 0);
        g.addArc("B", "C", 0);
        g.addArc("B", "D", 0);
        g.addArc("C", "D", 0);
        System.out.println(g.countCycles("B"));

    }
}
