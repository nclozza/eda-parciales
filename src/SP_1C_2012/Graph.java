package SP_1C_2012;

import java.util.*;

/**
 * Created by nclozza on 30/11/17.
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

  public boolean isDFS(List<V> values) {
    Node aux = nodes.get(values.get(0));
    if (aux == null) {
      return false;
    }

    clearMarks();
    LinkedList<V> valuesList = new LinkedList<>();
    valuesList.addAll(values);
    valuesList.removeFirst();

    return isDFSR(valuesList, aux);
  }

  private boolean isDFSR(LinkedList<V> values, Node node) {
    if (values.isEmpty()) {
      node.visited = true;
      return true;
    }

    node.visited = true;
    boolean stillUnvisitedNodes = false;

    for (Arc eachArc : node.adj) {
      if (!eachArc.neighbor.visited) {
        if (eachArc.neighbor.info == values.get(0)) {
          values.removeFirst();
          if (isDFSR(values, eachArc.neighbor)) {
            stillUnvisitedNodes = haveUnvisitedNodes(eachArc.neighbor);
            break;

          } else {
            return false;
          }

        } else {
          stillUnvisitedNodes = true;
        }
      } else {
        // Repeated node
        if (eachArc.neighbor.info == values.get(0)) {
          return false;
        }
      }
    }

    return !stillUnvisitedNodes;
  }

  private boolean haveUnvisitedNodes(Node node) {
    for (Arc eachArc : node.adj) {
      if (!eachArc.neighbor.visited) {
        return true;
      }
    }

    return false;
  }

  public Graph<V> subgraph(V v, int n) {
    Node origin = nodes.get(v);
    if (origin == null) {
      return null;
    }

    clearMarks();
    Graph<V> retGraph = new Graph<>();
    retGraph.addVertex(v);

    Stack<StackNode> stack = new Stack<>();
    stack.push(new StackNode(origin, 0));
    StackNode aux;
    boolean haveReachableNodes;

    while (!stack.isEmpty()) {
      aux = stack.peek();
      aux.node.visited = true;
      haveReachableNodes = false;

      for (Arc eachArc : aux.node.adj) {
        if (eachArc.neighbor.visited) {
          if (retGraph.nodes.containsKey(eachArc.neighbor.info)) {
            retGraph.addArc(aux.node.info, eachArc.neighbor.info, eachArc.weight);
          }

        } else {
          haveReachableNodes = true;
          if (aux.pathWeight + eachArc.weight <= n) {
            stack.push(new StackNode(eachArc.neighbor, aux.pathWeight + eachArc.weight));
            retGraph.addVertex(eachArc.neighbor.info);
            retGraph.addArc(aux.node.info, eachArc.neighbor.info, eachArc.weight);
          }
        }
      }

      if (!haveReachableNodes || aux.secondTime) {
        stack.remove(aux);

      } else {
        aux.secondTime = true;
      }
    }

    return retGraph;
  }

  private class StackNode {

    Node node;
    int pathWeight;
    boolean secondTime;

    StackNode(Node node, int pathWeight) {
      this.node = node;
      this.pathWeight = pathWeight;
      secondTime = false;
    }
  }
}
