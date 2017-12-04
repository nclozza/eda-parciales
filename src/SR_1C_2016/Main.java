package SR_1C_2016;

public class Main {
    public static void main(String[] args){
        Graph<String> g = new Graph<>();
        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
//        g.addVertex("D");
//        g.addVertex("E");
        g.addArc("A","B", 1.0);
//        g.addArc("A","E", 1.0);
        g.addArc("B","C", 1.0);
//        g.addArc("C","D", 1.0);
//        g.addArc("E","D", 1.0);
        System.out.println(g.isColoreable(2));


    }
}
