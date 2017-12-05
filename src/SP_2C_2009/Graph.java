package SP_2C_2009;

import java.util.*;

/**
 * Created by nico on 12/11/17.
 */
public class Graph<V> {
  private HashMap<V, Node> nodes = new HashMap<V, Node>();
  private List<Node> nodeList = new ArrayList<Node>();

  public void addVertex(V vertex, int weight) {
    if (!nodes.containsKey(vertex)) {
      Node node = new Node(vertex, weight);
      nodes.put(vertex, node);
      nodeList.add(node);
    }
  }

  public void addArc(V v, V w, int weight) {
    Node origin = nodes.get(v);
    Node dest = nodes.get(w);
    if (origin != null && dest != null && !origin.equals(dest)) {
      for (Arc arc : origin.adj) {
        if (arc.neighbor.info.equals(w)) {
          return;
        }
      }
      origin.adj.add(new Arc(weight, dest));
      dest.adj.add(new Arc(weight, origin));
    }
  }

  protected void clearMarksAndTags() {
    for (Node n : nodeList) {
      n.visited = false;
      n.tag = 0;
    }
  }

  private class Node {
    V info;
    int weight;
    boolean visited = false;
    int tag = 0;
    List<Arc> adj = new ArrayList<Arc>();

    public Node(V info, int weight) {
      this.info = info;
      this.weight = weight;
    }

    public int hashCode() {
      return info.hashCode();
    }

    public boolean equals(Object obj) {
      if (obj == null || !(obj.getClass() != getClass())) {
        return false;
      }
      return info.equals(((Node) obj).info);
    }
  }

  private class Arc {
    int weight;
    Node neighbor;

    public Arc(int weight, Node neighbor) {
      this.weight = weight;
      this.neighbor = neighbor;
    }
  }

  public int minWeight(V origin, V destiny) {
    if (!nodes.containsKey(origin) || !nodes.containsKey(destiny)) {
      throw new IllegalArgumentException();
    }

    clearMarksAndTags();
    Node o = nodes.get(origin);
    Node d = nodes.get(destiny);

    PriorityQueue<PQNode> q = new PriorityQueue<>();
    q.offer(new PQNode(o, o.weight));

    PQNode aux;

    while (!q.isEmpty()) {
      aux = q.poll();

      if (aux.node.info == d.info) {
        return aux.acum;
      }

      aux.node.visited = true;

      for (Arc eachArc : aux.node.adj) {
        if (!eachArc.neighbor.visited) {
          q.offer(new PQNode(eachArc.neighbor, eachArc.weight + aux.acum + eachArc.neighbor.weight));
        }
      }
    }

    return 0;
  }

  private class PQNode implements Comparable<PQNode> {

    Node node;
    int acum;

    public PQNode(Node node, int acum) {
      this.node = node;
      this.acum = acum;
    }

    @Override
    public int compareTo(PQNode pqNode) {
      return acum - pqNode.acum;
    }
  }
}
