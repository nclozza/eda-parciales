package SP_2C_2017_bis;

import java.util.*;

/**
 * Created by nclozza on 03/12/17.
 */
public class Graph<V> {

  private HashMap<V, Node> nodes = new HashMap<V, Node>();
  private List<Node> nodeList = new ArrayList<Node>();

  public void addVertex(V vertex) {
    if (!nodes.containsKey(vertex)) {
      Node node = new Node(vertex);
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

  private class Node {
    V info;
    boolean visited = false;
    List<Arc> adj = new ArrayList<Arc>();

    public Node(V info) {
      this.info = info;
    }

    public int hashCode() {
      return info.hashCode();
    }

    public boolean equals(Object obj) {
      if (obj == null || obj.getClass() != getClass()) {
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

  private void clearMarks() {
    for (Node eachNode : nodeList) {
      eachNode.visited = false;
    }
  }

  public boolean isPath(int n) {

    for (Node eachNode: nodeList) {
      clearMarks();
      eachNode.visited = true;
      Package pack = new Package();
      if (!thereIsPathR(eachNode, n, pack, nodeList.size())) {
        return false;
      }
    }

    return true;
  }


  private boolean thereIsPathR(Node origin, int n, Package pack, int totalNodes) {
    if (pack.count == totalNodes) {
      return true;
    }

    for (Arc eachArc : origin.adj) {
      if (eachArc.weight <= n && !eachArc.neighbor.visited) {
        eachArc.neighbor.visited = true;
        pack.count++;
        if (thereIsPathR(eachArc.neighbor, n - eachArc.weight, pack, totalNodes)) {
          return true;
        }

        eachArc.neighbor.visited = false;
      }
    }

    return false;
  }

  private static class Package {

    int count = 0;

  }
}

