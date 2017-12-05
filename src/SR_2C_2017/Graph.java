package SR_2C_2017;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nclozza on 05/12/17.
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
    int tag = 0;

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

  private void clearMarksAndTags() {
    for (Node eachNode : nodeList) {
      eachNode.visited = false;
      eachNode.tag = 0;
    }
  }

  /**
   * Return true if the given node can reach all the others with less weight than maxWeight
   * The weights can be positive or negatives
   */
  public boolean pathForAll(V node, int maxWeight) {
    Node origin = nodes.get(node);
    if (origin == null) {
      throw new IllegalArgumentException();
    }

    clearMarksAndTags();
    return pathForAllR(origin, 0, maxWeight, new VisitedCount());
  }

  private boolean pathForAllR(Node node, int actualWeight, int maxWeight, VisitedCount visitedCount) {

    if (node.tag == 0 && actualWeight <= maxWeight) {
      visitedCount.count++;
      node.tag = 1;
    }

    if (visitedCount.count == nodeList.size()) {
      return true;
    }

    node.visited = true;
    for (Arc eachArc : node.adj) {
      if (!eachArc.neighbor.visited) {
        if (pathForAllR(eachArc.neighbor, actualWeight + eachArc.weight, maxWeight, visitedCount)) {
          return true;
        }
      }
    }

    node.visited = false;

    return false;
  }

  private static class VisitedCount {
    int count = 0;
  }
}
