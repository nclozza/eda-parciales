package SR_1C_2010;

public class Main {
    public static void main(String[] args){
        Graph<String> g = new Graph<>();
        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("D");
        g.addArc("A","B", 1);
        g.addArc("A","C",3);
        g.addArc("B","C",1);
        g.addArc("B","D",8);
        g.addArc("C","D",100);
//        System.out.println(g.maxDistance("A","D"));
        System.out.println(g.counCycles());
    }
}
