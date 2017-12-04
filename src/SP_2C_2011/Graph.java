package SP_2C_2011;

import java.util.*;

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

    public void addArc(V v, V w) {
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

    private void clearMarks() {
        for(Node n : nodeList)
            n.visited = false;
    }

    private void clearTags() {
        for(Node n : nodeList)
            n.tag = 0;
    }

    public List<V> eulerianCycles(){
        int cantArc = checkGrade();
        if (cantArc % 2 != 0)
            return null;
        cantArc /= 2;
        List<V> l = new LinkedList<>();
        Stack<Node> s = new Stack<>();
        Node origin = nodeList.get(0); //agarro cualquiera, si existe un ciclo euleriano, existe desde cualquier nodo
        s.push(origin);
        l.add(origin.info);
        clearMarks();clearTags();
        while(!s.isEmpty()){
            Node aux = s.peek();
            for(Arc a : aux.adj){
                if (a.neighbor.tag != 0 && a.neighbor != origin) {
                    a.neighbor.tag = aux.tag + 1;
                    l.add(a.neighbor.info);
                } if (a.neighbor == origin && cantArc == 0){
                    l.add(origin.info);
                    return l;
                }
            }
        }
        return l;
    }

    private int checkGrade() {
        int aux = 0;
        for(Node n : nodeList) {
            aux += n.adj.size();
        }
        return aux;
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