package OK;

import java.util.LinkedList;

public class Test {
    LinkedList<Node> l = new LinkedList<>();
    LinkedList<Node> l2 = new LinkedList<>();


    public Test(Integer test) {
         Node aux = new Node(test);
        l.add(aux);
        l2.add(aux);
    }

    public boolean remove(){
        l = null;
        for( Node n : l2){
            System.out.println(n.value);
        }
        return true;
    }

    public static void main(String[] args) {
        Test t = new Test(2);
        System.out.println(t.remove());
    }

    private static class Node {
        Integer value;

        public Node(Integer value) {
            this.value = value;
        }
    }
}
