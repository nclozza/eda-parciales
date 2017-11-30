package SP_1C_2016;

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

    public boolean isBFS(List<V> values){
        Node origin = nodes.get(values.get(0));
        if (origin == null)
            throw new IllegalArgumentException("ILLEGAL");
        Queue<Node> q = new LinkedList<>();
        clearMarks();
        origin.visited = true;
        q.offer(origin);
        int auxi = 1;
        boolean error = false;
        while(!q.isEmpty() && auxi < values.size()){
            Node aux = q.poll();
            for(Arc a : aux.adj){
                error = false;
                if(!a.neighbor.visited && a.neighbor.info.equals(values.get(auxi))){
                    auxi++;
                    a.neighbor.visited = true;
                    q.offer(a.neighbor);
                } else if (!a.neighbor.info.equals(values.get(auxi)))
                    error = true;
            }
            if(error)
                return false;
        }
        return true;
    }

    private void clearMarks() {
        for(Node n : nodeList)
            n.visited = false;
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
