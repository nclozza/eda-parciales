package PP_1C_2011;

public class MyBag<T> implements Bag<T>{

    private Node<T> header;
    private int maxCount = 0;

    public MyBag() {
        header = null;
    }

    @Override
    public void add(T elem) {
        if (header == null && elem != null) {
            header = new Node<>(elem, null);
            header.setPrev(null);
            header.setCount(header.getCount()+1);
            maxCount = header.getCount();
            return;
        } else if (header != null && elem != null) {
            addC(header, elem);
            return;
        } else
            throw new IllegalArgumentException("Illegal Argument");
    }

    private void addC(Node<T> node, T elem) {
        if (node.getNext() == null && !node.getValue().equals(elem)){
            Node<T> aux = new Node<>(elem, null);
            aux.setPrev(node);
            aux.setCount(aux.getCount()+1);
            node.setNext(aux);
            return;
        } else if (node.getValue().equals(elem)){
            node.setCount(node.getCount()+1);
            if (maxCount < node.getCount()){ //pongo a node (mayor aparicion) al principio, asi garantizo que siempre
                Node<T> aux = node;          // se impriman en orden dependiendo la cant. de apariciones
                node.getPrev().setNext(aux.getNext());
                Node<T> aux2 = header;
                aux2.setPrev(aux);
                aux.setNext(aux2);
                aux.setPrev(null);
                header = aux;
                maxCount = node.getCount();
                return;
            }
            return;
        } else{
            addC(node.getNext(), elem);
        }
    }

    @Override
    public void remove(T elem) {
        if (header == null || elem == null) {
            throw new IllegalArgumentException("ILLEGAL");
        } else if (elem != null){
            removeC(header, elem);
        }
    }

    private void removeC(Node<T> node, T elem) {
        if (node == null){ //el T elem no está en la bag
            throw new IllegalArgumentException("ILLEGAL");
        } else if (node.getValue().equals(elem)) { //lo encontré
            if (node == header) {
                header = node.getNext();
                maxCount = header.getCount();
            } else {
                Node<T> aux = node.getNext();
                Node<T> prev = node.getPrev();
                node = aux;
                prev.setNext(node);
            }
            /*node.setValue(node.getNext().getValue()); OTRA ALTERNATIVA, FEA, PERO FUNCA
            node.setCount(node.getNext().getCount());
            node.setNext(node.getNext().getNext());*/
            return;
        } else {
            removeC(node.getNext(), elem);
        }
    }

    @Override
    public void print() {
        Node<T> aux = header;
        while (aux != null) {
            System.out.print(aux.getValue().toString() + "(" + aux.getCount() + ") ");
            aux = aux.getNext();
        }
        System.out.println("");
        return;
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
