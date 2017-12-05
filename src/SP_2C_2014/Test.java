package SP_2C_2014;

/**
 * Created by nico on 12/11/17.
 */
public class Test {

  public static void main(String[] str) {
    Graph<String> graph = new Graph<>();

    graph.addVertex("A");
    graph.addVertex("B");
    graph.addVertex("C");
    graph.addVertex("D");
    graph.addVertex("E");

    graph.addArc("A", "B", 4.0);
    graph.addArc("A", "D", 2.0);
    graph.addArc("A", "E", 6.0);
    graph.addArc("B", "C", -2.0);
    graph.addArc("B", "E", 7.0);
    graph.addArc("D", "E", 1.0);

    System.out.println(graph.weightPath(7.0));
  }

}
