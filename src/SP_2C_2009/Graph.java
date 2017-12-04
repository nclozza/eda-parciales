package SP_2C_2009;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
            origin.adj.add(new Arc(dest, weight));
            dest.adj.add(new Arc(origin, weight));
        }
    }

    private void clearMarks() {
        for(Node n : nodeList)
            n.visited = false;
    }

    private void clearTags() {
        for(Node n : nodeList)
            n.tag = 0;
    }

    public int countCycles(V node){
        Node origin = nodes.get(node);
        if (origin == null || node == null)
            throw new IllegalArgumentException();
        int aux = 0;
        for (Arc a : origin.adj){
            clearMarks();
            clearTags();
            a.neighbor.visited = true;
            a.neighbor.tag = 1;
            aux += countR(a.neighbor, origin);
        }
        return aux/2;
    }

    private int countR(Node node, Node origin) {
        int ret = 0;
        if (node == origin && node.tag != 1)
            return 1;
        else if (node == origin)
            return 0;
        else{
            for (Arc a : node.adj){
                if (a.neighbor == origin && node.tag != 1){
                    ret += 1;
                } else if (!a.neighbor.visited && a.neighbor != origin){
                    a.neighbor.tag = node.tag+1;
                    a.neighbor.visited = true;
                    ret += countR(a.neighbor, origin);
                    a.neighbor.tag = 0;
                    a.neighbor.visited = false;
                }
            }
        }
        return ret;
    }

    private class Node {
        V info;
        boolean visited = false;
        int weight;
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
            if (obj == null || (obj.getClass() != getClass())) {
                return false;
            }
            return info.equals(((Node)obj).info);
        }
    }
    private class Arc {
        Node neighbor;
        int weight;

        public Arc(Node neighbor, int weight) {
            this.neighbor = neighbor;
            this.weight = weight;
        }
    }
}
