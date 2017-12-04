package SR_1C_2010;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph<V> {

    private HashMap<V, Node> nodes = new HashMap<V, Node>();  private List<Node> nodeList = new ArrayList<Node>();

    public void addVertex(V vertex) {   if (!nodes.containsKey(vertex)) {    Node node = new Node(vertex);    nodes.put(vertex, node);    nodeList.add(node);   }  }

    public void addArc(V v, V w, int weight) {   Node origin = nodes.get(v);   Node dest = nodes.get(w);   if (origin != null && dest != null && !origin.equals(dest)) {    for (Arc arc : origin.adj) {     if (arc.neighbor.info.equals(w)) {      return;     }    }    origin.adj.add(new Arc(weight, dest));    dest.adj.add(new Arc(weight, origin));   }  }

    private class Node {   V info;   boolean visited = false;   int tag = 0;   List<Arc> adj = new ArrayList<Arc>();

        public Node(V info) {    this.info = info;   }   public int hashCode() {    return info.hashCode();   }

        public boolean equals(Object obj) {    if (obj == null || obj.getClass() != getClass()) {     return false;    }    return info.equals(((Node)obj).info);   }  }  private class Arc {   int weight;   Node neighbor;

        public Arc(int weight, Node neighbor) {    this.weight = weight;    this.neighbor = neighbor;   }  }

    public int maxDistance(V ori, V des){
        Node origin = nodes.get(ori);
        Node destiny = nodes.get(des);
        if (origin == null || destiny == null)
            throw new IllegalArgumentException();
        origin.visited = true;
        searchR(origin, destiny, 0);
        return destiny.tag;
    }

    private void searchR(Node node, Node destiny, int acum) {
        if(node == destiny){
            if (acum > destiny.tag){
                destiny.tag = acum;
                return;
            }
        }
        for (Arc a : node.adj){
            if(!a.neighbor.visited){
                a.neighbor.visited = true;
                searchR(a.neighbor, destiny, acum+a.weight);
                a.neighbor.visited = false;
            }
        }
    }

    public int counCycles(){
        int count = 0;
        for(Node n: nodeList){
            count += countCycle(n, n);
        }
        count = count/(2*nodeList.size()); //porque cada ciclo me lo cuenta en los dos sentidos
        return count;
    }

    private int countCycle(Node origin,Node node){
       if(node == origin && node.visited == true){
           return 1;
       }

        node.visited = true;
        int count = 0;
        for(Arc a: node.adj){
            if(!a.neighbor.visited || (a.neighbor == origin )){
                count += countCycle(origin, a.neighbor);
            }
        }
        node.visited = false;
        return count;
    }
}