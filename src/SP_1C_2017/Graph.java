package SP_1C_2017;

import java.util.*;

/**
 * Created by nclozza on 04/12/17.
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

  public boolean orderedPath(Comparator<V> cmp) {
    if (nodeList.size() == 0 || !isRelated()) {
      return false;
    }

    Node first = nodeList.get(0);

    for (Node eachNode : nodeList) {
      if (cmp.compare(first.info, eachNode.info) > 0) {
        first = eachNode;
      }
    }

    clearMarks();
    first.visited = true;
    boolean ret = orderedPathR(first, cmp, 1, nodeList.size());
    first.visited = false;

    return ret;
  }

  private boolean orderedPathR(Node node, Comparator<V> cmp, int count, int totalNodes) {
    if (count == totalNodes) {
      return true;
    }

    Node smaller = null;

    for (Arc eachArc : node.adj) {
      if (!eachArc.neighbor.visited) {
        if (cmp.compare(node.info, eachArc.neighbor.info) > 0) {
          return false;
        }

        if (smaller == null || cmp.compare(smaller.info, eachArc.neighbor.info) > 0) {
          smaller = eachArc.neighbor;
        }
      }
    }

    if (smaller == null) {
      return false;
    }

    smaller.visited = true;
    return orderedPathR(smaller, cmp, ++count, totalNodes);
  }

  private boolean isRelated() {
    for (Node eachNode : nodeList) {
      if (eachNode.adj.size() == 0) {
        return false;
      }
    }

    return true;
  }

  public Set<String> palindromeWords() {

    Set<String> set = new HashSet<>();
    clearMarks();

    for (Node eachNode : nodeList) {
      palindromeWordsR(eachNode, set, eachNode.info.toString());
    }

    return set;
  }

  private void palindromeWordsR(Node node, Set<String> set, String str) {
    if (isPalindrome(str)) {
      set.add(str);
    }

    node.visited = true;

    for (Arc eachArc : node.adj) {
      if (!eachArc.neighbor.visited) {
        palindromeWordsR(eachArc.neighbor, set, str + eachArc.neighbor.info);
      }
    }

    node.visited = false;
  }

  private boolean isPalindrome(String str) {
    char[] word = str.toCharArray();
    int i1 = 0;
    int i2 = word.length - 1;
    while (i2 > i1) {
      if (word[i1] != word[i2]) {
        return false;
      }
      ++i1;
      --i2;
    }

    return true;
  }
}