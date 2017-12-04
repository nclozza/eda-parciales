package SR_1C_2016;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
            return info.equals(((Node)obj).info);
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

    public void clearTagsAndMarks(){
        for(Node n : nodeList) {
            n.tag = 0;
            n.visited = false;
        }
    }

    public boolean isColoreable(int n){
        for (Node node : nodeList){
            clearTagsAndMarks();
            colorR(node,  n);
            for(Node na : nodeList)
                System.out.println(na.info +" tag: " + na.tag);
            if (check())
                return true;
        }
        return false;
    }

    private boolean check() {
        for(Node node : nodeList){
            int aux = node.tag;
            for(Arc a : node.adj){
                if(a.neighbor.tag == aux || a.neighbor.tag == 0)
                    return false;
            }
        }
        return true;
    }

    private void colorR(Node node, int n) {
        if(!node.visited) {
            boolean[] av = new boolean[n];
            for(int i = 0 ; i < av.length ;i++)
                av[i]=true;
            node.visited = true;
            for(Arc a : node.adj){
                if(a.neighbor.tag != 0){
                    av[a.neighbor.tag-1] = false;
                }
            }
            for(int i = 0 ; i < av.length;i++) {
                if (av[i]) {
                    node.tag = i + 1;
                    for (Arc a : node.adj) {
                        if (!a.neighbor.visited)
                            colorR(a.neighbor, n);
                    }
                    return;
                }
            }
        }
        return;
    }


}
