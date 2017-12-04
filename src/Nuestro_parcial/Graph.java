package Nuestro_parcial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph{

    private HashMap<Double, Node> nodes = new HashMap<Double, Node>();
    private List<Node> nodeList = new ArrayList<Node>();

    public void addVertex(Double vertex) {
        if (!nodes.containsKey(vertex)) {
            Node node = new Node(vertex);
            nodes.put(vertex, node);
            nodeList.add(node);
        }
    }

    public void addArc(Double v, Double w) {
        Node origin = nodes.get(v);
        Node dest = nodes.get(w);
        if (origin != null && dest != null && !origin.equals(dest)) {
            for (Arc arc : origin.adj) {
                if (arc.neighbor.info.equals(w)) {
                    return;
                }
            }
            origin.adj.add(new Arc(dest));
            dest.adj.add(new Arc(origin));
        }
    }

    public int pathForSum(double n, Double start){
        Node origin = nodes.get(start);
        if (origin == null)
            throw new IllegalArgumentException();
        int count = 0;
        clearMarks();
        origin.visited = true;
        count += pathR(origin,origin.info, n);
        return count;
    }

    private int pathR(Node node, Double acum, double n) {
        int aux = 0;
        if (node == null)
            return 0;
        if( acum == n )
            aux += 1;
        for (Arc a : node.adj){
            if (!a.neighbor.visited){
                a.neighbor.visited = true;
                aux += pathR(a.neighbor, acum+a.neighbor.info, n);
                a.neighbor.visited = false;
            }
        }
        return aux;
    }

    private void clearMarks() {
        for(Node n : nodeList)
            n.visited = false;
    }


    private void clearTags() {
        for(Node n : nodeList)
            n.tag = 0;
    }

    private class Node {
        Double info;
        boolean visited = false;
        int tag = 0;
        List<Arc> adj = new ArrayList<Arc>();

        public Node(Double info) {
            this.info = info;
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

        public Arc(Node neighbor) {
            this.neighbor = neighbor;
        }
    }
}