package OK;

import java.util.Comparator;

public class PriorityQueue<V> {

    Node<V> header = null;
    Comparator<V> comp;
    int max;
    int current = 0;

    public PriorityQueue(Comparator<V> comp, int n) {
        this.comp = comp;
        max = n;
    }

    public boolean enqueue(V value){
        if (header == null && max > 0){
            header = new Node<>(value);
            return true;
        } else if (current == max ) {
            return false;
        } else if (value == null){
            return false;
        } else {
            enqueueR(header, value);
            return true;
        }
    }

    private void enqueueR(Node<V> node, V value) {
        if(node == null){
            node = new Node<>(value);
            return;
        } else if (node.left != null && node.right != null){
            if(comp.compare(node.value, value) < 0){ // aca tengo que insertar
                Node<V> aux = new Node<>(value);
                aux.right = node;
                node = aux;
                return;
            } else if (comp.compare(node.value, value) > 0){
                if(node.right.complete && node.left.complete)
                    enqueueR(node.right, value);
                else if(node.right.complete)
                    enqueueR(node.left,value);
                else
                    enqueueR(node.right, value);
            }
        } else if (node.left == null && node.right != null){
            if (comp.compare(node.value, value) < 0){
                Node<V> aux = node;
                node = new Node<>(value);
                node.left = aux;
                node.complete = true;
            } else
                node.left= new Node<>(value);
        } else if (node.right == null && node.left != null){
            if (comp.compare(node.value, value) < 0){
                Node<V> aux = node;
                node = new Node<>(value);
                node.right = aux;
                node.complete = true;
            } else
                node.right= new Node<>(value);
        } else{
            if (comp.compare(node.value, value) < 0){
                Node<V> aux = node;
                node = new Node<>(value);
                node.left = aux;
            } else
                node.left = new Node<>(value);
        }
    }

    private class Node<V> {
        V value;
        Node<V> left = null;
        Node<V> right = null;
        boolean complete = false;

        public Node(V value) {
            this.value = value;
        }

        public Node<V> getLeft() {
            return left;
        }

        public void setLeft(Node<V> left) {
            this.left = left;
        }

        public Node<V> getRight() {
            return right;
        }

        public void setRight(Node<V> right) {
            this.right = right;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
