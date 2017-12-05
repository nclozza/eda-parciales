package SP_2C_2009;

/**
 * Created by nico on 12/11/17.
 */
public class Test {

  public static void main(String[] str) {
    Graph<Integer> graph = new Graph<>();

    graph.addVertex(1, 2);
    graph.addVertex(2, 9);
    graph.addVertex(3, 1);
    graph.addVertex(4, 2);

    graph.addArc(1, 2, 1);
    graph.addArc(1, 3, 3);
    graph.addArc(2, 3, 1);
    graph.addArc(2, 4, 8);
    graph.addArc(3, 4, 1);

    System.out.println(graph.minWeight(1, 4));
  }
}
