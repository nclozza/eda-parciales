package SP_1C_2012;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args){
            Graph<String> g = new Graph<>();
        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("D");

        g.addVertex("E");
        g.addVertex("F");
        g.addArc("A","D", 10);
        g.addArc("A","C", 1);
        g.addArc("A","B", 8);
        g.addArc("B","C", 5);
        g.addArc("B","E", 9);
        g.addArc("C","D", 6);
        g.addArc("C","F", 2);
        g.addArc("E","F", 4);
        g.addArc("F","D", 1);
        Graph<String> ga = g.subgraph("A", 5);
        System.out.println(ga.getNodeList().size());


    }
}
