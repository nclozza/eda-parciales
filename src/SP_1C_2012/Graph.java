package SP_1C_2012;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Graph<V> {private HashMap<V, Node> nodes = new HashMap<V, Node>();  private List<Node> nodeList = new ArrayList<Node>();

    public void addVertex(V vertex) {   if (!nodes.containsKey(vertex)) {    Node node = new Node(vertex);    nodes.put(vertex, node);    nodeList.add(node);   }  }  public void addArc(V v, V w, int weight) {   Node origin = nodes.get(v);   Node dest = nodes.get(w);   if (origin != null && dest != null && !origin.equals(dest)) {    for (Arc arc : origin.adj) {     if (arc.neighbor.info.equals(w)) {      return;     }    }    origin.adj.add(new Arc(weight, dest));    dest.adj.add(new Arc(weight, origin));   }  }

    public List<Node> getNodeList() {
        return nodeList;
    }

    private class Node {   V info;   boolean visited = false;   List<Arc> adj = new ArrayList<Arc>();

        public Node(V info) {    this.info = info;   }   public int hashCode() {    return info.hashCode();   }

        public boolean equals(Object obj) {    if (obj == null || obj.getClass() != getClass()) {     return false;    }    return info.equals(((Node)obj).info);   }  }  private class Arc {   int weight;   Node neighbor;

        public Arc(int weight, Node neighbor) {    this.weight = weight;    this.neighbor = neighbor;   }  }

        public boolean isDFS(List<V> values){
            Node origin = nodes.get(values.get(0));
            if (origin == null)
                throw new IllegalArgumentException("ILLEGAL");
            Stack<Node> s = new Stack<>();
            clearMarks();
            origin.visited = true;
            boolean error = false;
            s.push(origin);
            int i = 1;
            while(!s.isEmpty()){
                Node aux = s.peek(); //Miro cual es el nodo que tengo arriba, no lo saco
                boolean contains = false;
                for (Arc a : aux.adj){
                    error = false;
                    if(a.neighbor.visited == false && a.neighbor.info.equals(values.get(i))) {
                        System.out.println(a.neighbor.info.toString() + " OK");
                        contains = true;
                        i++;
                        a.neighbor.visited= true;
                        s.push(a.neighbor);
                    } else if (a.neighbor.visited == false && !a.neighbor.info.equals(values.get(i)))
                        error = true;
                }
                if(contains == false && error) //es  recorrido BFS
                    return false;
                else if (contains == false && i != values.size() && !error) //los nodos adyacentes estan todos visitados, "backtrack"
                    s.pop();
                else if (i == values.size() && contains ) //Recorri toda la lista y esta ok
                    return true;
                else if (!contains && i == values.size()) { //Recorri toda la lista y no encontre algun nodo en el grafo
                    System.out.println("ACA");
                    return false;
                }
                contains = false;
            }
            return false;
        }


    private class SNode{
            Node n;
            int tag;

        public SNode(Node n, int tag) {
            this.n = n;
            this.tag = tag;
        }
    }

    public Graph<V> subgraph(V v, int n){
        Node origin = nodes.get(v);
        if(origin == null)
            return null;
        Stack<SNode> s = new Stack<>();
        s.push(new SNode(origin, 0));
        clearMarks();
        origin.visited = true;
        Graph<V> graph = new Graph<>();
        graph.addVertex(origin.info);
        while(!s.isEmpty()){
            SNode aux = s.pop();
            for (Arc a : aux.n.adj){
                if (!a.neighbor.visited && a.weight < (n - aux.tag)){
                    graph.addVertex(a.neighbor.info);

                    s.push(new SNode(a.neighbor, a.weight+aux.tag));
                    a.neighbor.visited = true;
                }
            }
        }
        buildArcs(graph);
        return graph;
    }

    private void buildArcs(Graph<V> graph) {
        List<Node> list = graph.nodeList;
        clearMarks();
        for (Node n : list){
            for (Node n2 : this.nodeList){
                if (n == n2){
                    for (Arc a : n2.adj){
                        if (list.contains(a.neighbor) && a.neighbor.visited == false)
                            graph.addArc(n.info, a.neighbor.info, a.weight);
                    }
                }
            }
        }
        return;
    }

    private void clearMarks() {
        for (Node n : nodeList)
            n.visited = false;
    }
}