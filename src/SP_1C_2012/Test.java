package SP_1C_2012;

import java.util.LinkedList;

/**
 * Created by nclozza on 30/11/17.
 */
public class Test {

  public static void main(String[] str) {

    /*
    Graph<String> g = new Graph<>();

    g.addVertex("A");
    g.addVertex("B");
    g.addVertex("C");
    g.addVertex("D");
    g.addVertex("E");

    g.addArc("A", "B", 0);
    g.addArc("A", "D", 0);
    g.addArc("C", "B", 0);
    g.addArc("D", "B", 0);
    g.addArc("E", "B", 0);
    g.addArc("E", "C", 0);

    LinkedList<String> list = new LinkedList<>();
    list.add("B");
    list.add("E");
    list.add("D");
    list.add("A");
    list.add("C");

    System.out.println(g.isDFS(list));
    */

    Graph<String> g = new Graph<>();

    g.addVertex("A");
    g.addVertex("B");
    g.addVertex("C");
    g.addVertex("D");
    g.addVertex("E");
    g.addVertex("F");

    g.addArc("A", "B", 8);
    g.addArc("A", "D", 10);
    g.addArc("A", "C", 1);
    g.addArc("B", "C", 5);
    g.addArc("B", "E", 9);
    g.addArc("C", "D", 6);
    g.addArc("C", "E", 7);
    g.addArc("C", "F", 2);
    g.addArc("D", "F", 1);
    g.addArc("E", "F", 4);

    Graph<String> graph = g.subgraph("A", 5);
  }
}
