package SP_2C_2014;

import java.util.*;

/**
 * Created by nico on 12/11/17.
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

  public void addArc(V v, V w, Double d) {
    Node origin = nodes.get(v);
    Node dest = nodes.get(w);
    if (origin != null && dest != null && !origin.equals(dest)) {
      for (Arc arc : origin.adj) {
        if (arc.neighbor.info.equals(w)) {
          return;
        }
      }
      origin.adj.add(new Arc(dest, d));
      dest.adj.add(new Arc(origin, d));
    }
  }

  private class Node {
    V info;
    boolean visited = false;
    int tag = 0;
    List<Arc> adj = new ArrayList<Arc>();

    public Node(V info) {
      this.info = info;
    }

    public int hashCode() {
      return info.hashCode();
    }

    public boolean equals(Object obj) {
      if (obj == null || (obj.getClass() != getClass())) {
        return false;
      }
      return info.equals(((Node) obj).info);
    }
  }

  private class Arc {
    Node neighbor;
    Double weight;

    public Arc(Node neighbor, Double weight) {
      this.neighbor = neighbor;
      this.weight = weight;
    }
  }

  public List<V> weightPath(double weight) {
    if (nodes.isEmpty()) {
      return null;
    }

    List<V> list = new LinkedList<>();

    for (Node eachNode : nodeList) {
      if (weightPathR(eachNode, weight, list, 0)) {
        list.add(eachNode.info);
        return list;
      }
    }

    return null;
  }

  private boolean weightPathR(Node node, double weight, List<V> linkedList, double acum) {
    if (acum == weight) {
      return true;

    } else {
      node.visited = true;

      for (Arc eachArc : node.adj) {
        if (!eachArc.neighbor.visited) {
          if (weightPathR(eachArc.neighbor, weight, linkedList, acum + eachArc.weight)) {
            linkedList.add(eachArc.neighbor.info);
            return true;
          }
        }
      }

      node.visited = false;
      return false;
    }
  }
}
