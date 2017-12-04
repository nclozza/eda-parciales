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

    public boolean hasCenter(int n){
        int current;
        int nodeCant;
        for (Node node : nodeList) {
            nodeCant = nodeList.size()-1;
            current = 0;
            Queue<Node> q = new LinkedList<>();
            clearMarks();
            clearTags();
            node.tag = current++;
            node.visited = true;
            q.offer(node);
            while(!q.isEmpty()){
                Node aux = q.poll();
                if(current > n) {
                    break;
                }
                for(Arc a : aux.adj){
                    if (!a.neighbor.visited){
                        System.out.println("Tageo " + a.neighbor.info + " con current en: " + current);
                        a.neighbor.tag = current;
                        a.neighbor.visited = true;
                        q.offer(a.neighbor);
                        nodeCant--;
                    }
                }
                if(current <= n && nodeCant == 0)
                    return true;
                current++;
            }
        }
        return false;
    }

    private void clearTags() {
        for(Node n : nodeList)
            n.tag = 0;
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
