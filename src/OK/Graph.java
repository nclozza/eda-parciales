package OK;

import java.util.*;

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

    public void clearMarks(){
        for (Node n : nodeList){
            n.visited = false;
        }
        return;
    }

    public int maxPathIterative(V from, V to) { //Iterativo, asumo que hay peso en aristas y nodos
        Node origin = nodes.get(from);
        Node destiny = nodes.get(to);
        if (origin == null || destiny == null)
            throw new IllegalArgumentException("ILLEGAL");
        int maxacum = 0;
        clearMarks();
        Queue<Node> q = new LinkedList<>();
        origin.tag = origin.weight;
        q.offer(origin);
        origin.visited = true;
        while(!q.isEmpty()) {
            Node aux = q.poll();
            if (aux == destiny){
                aux.visited = false;
                if (aux.tag > maxacum)
                    maxacum = aux.tag;
            } else{
                for (Arc a : aux.adj){
                    if (!a.neighbor.visited) {
                        a.neighbor.tag = aux.tag + a.neighbor.weight + a.weight;
                        q.offer(a.neighbor);
                        a.neighbor.visited = true;
                    }
                }
            }
        }
        return maxacum;
    }

    public int minHamiltonianPath(){
        int minGrade = checkMinGrade();
        if (minGrade == -1) //significa que no es conexo
            return -1;
        Node origin = nodeList.get(minGrade);
        if (origin == null)
            throw new RuntimeException("GRAPH IS EMPTY");
        PriorityQueue<PQNode> pq = new PriorityQueue<>();
        origin.visited = true;
        pq.offer(new PQNode(origin, 0));
        int count = nodeList.size();
        int acum = 0;
        while (!pq.isEmpty() || count > 0){
            PQNode aux = pq.poll();
            aux.node.visited = true;
            if (aux.node != origin)
                count--;
            for (Arc a : aux.node.adj){
                if (!a.neighbor.visited) {
                    acum = aux.weight + a.weight;
                    pq.offer(new PQNode(a.neighbor, acum));
                }
            }
        }
        return count == 0 ? acum : -1;
    }

    private int checkMinGrade() {
        int min = nodeList.get(0).adj.size();
        for (int i = 1 ; i < nodeList.size();i++) {
            if (nodeList.get(i).adj.size() < min) {
                min = i;
            }
        }
        if (min ==nodeList.get(0).adj.size())
            return 0;
        else if (min == 0)
            return -1;
        else
            return min;
    }

    private class PQNode implements Comparable<PQNode>{
        Node node;
        int weight;

        public PQNode(Node node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(PQNode o) {
            return weight - o.weight;
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
}


