package OK;

public class Main {
    public static void main(String[] args){
        Graph<String> g = new Graph<>();
        g.addVertex("A", 1);
        g.addVertex("B", 3);
        g.addVertex("C", 1);
        g.addVertex("D", 1);
        g.addVertex("E", 1);
        g.addArc("B", "C", 1);
        g.addArc("A", "C", 1);
        g.addArc("B", "D", 2);
        g.addArc("C", "D", 10);
//        System.out.println(g.maxPathIterative("A","B"));
        System.out.println(g.minHamiltonianPath());
    }
}
