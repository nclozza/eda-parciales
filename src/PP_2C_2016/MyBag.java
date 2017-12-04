package PP_2C_2016;

import java.util.Comparator;

public class MyBag<T> implements Bag<T> {

    Node<T> header;
    Comparator<T> comp;

    public MyBag(Comparator<T> comp) {
        this.comp = comp;
    }

    @Override //orden N
    public void add(T value) {
        if (header == null){
            header = new Node<>(value, null);
            header.setPrev(null);
            header.setCount(1);
        } else
            addR(header, value);
    }

    private void addR(Node<T> node, T value) {
        if( node != null && comp.compare(node.value, value) == 0){
            node.setCount(node.count+1);
            if(node.getPrev() != null && node.getPrev().getCount() < node.count){
                Node<T> aux = node;
                node.getPrev().setNext(node.next);
                fix(header, aux);
                return;
            }
        } else if(node.next == null){
            node.next = new Node<>(value, null);
            node.next.setCount(1);
            node.next.setPrev(node);
        }
        else
            addR(node.next, value);
    }

    private void fix(Node<T> node, Node<T> current) {
        if(node.count < current.count){
            if (node == header){
                Node<T> aux = node;
                header = current;
                current.prev = null;
                current.next = aux;
                aux.next = node.next;
                aux.prev = current;
            } else{
                Node<T> aux = node;
                current.prev = node.prev;
                node.prev.next = current;
                aux.prev = current;
                node = current;
                current.next = aux;
            }
        } else
            fix(node.next, current);
        return;

    }

    /*Node node = the node passed in
    Node previousNode = node.previous
    Excepting the special case where previousNode is null:

    previousNode.next = node.next;         // this used to point to node
    previousNode.previous.next = node;     // this used to point to previousNode
    node.previous = previousNode.previous; // this used to point to previousNode
    node.next.previous = previousNode;     // this used to point to node
    node.next = previousNode;
    previousNode.previous = node;
*/


    public void print() {
        Node<T> aux = header;
        while (aux != null) {
            System.out.print(aux.getValue().toString() + "(" + aux.getCount() + ") ");
            aux = aux.getNext();
        }
        System.out.println("");
        return;
    }

    @Override //Orden N
    public int occurrencesOf(T value) {
        return 0;
    }

    @Override //Orden 1
    public T removeMostPopular() {
        return null;
    }

    private class Node<T>{
        private int count = 0;
        private T value;
        private Node<T> next;
        private Node<T> prev;

        public Node<T> getPrev() {
            return prev;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }


        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
