package PP_1C_2013;

import java.util.Comparator;

public class MySortedFastList<T> implements SortedFastList<T> {

    private Comparator<T> comp;
    private Node<T> header;

    public MySortedFastList(Comparator<T> comp, T value) {
        this.comp = comp;
        this.header = new Node<>(value);
    }

    public MySortedFastList(Comparator<T> comp) {
        this.comp = comp;
    }

    @Override
    public void add(T value) {
        if (value == null)
            throw new IllegalArgumentException("ILLEGAL");
        if (header == null){
            header = new Node<>(value);
            return;
        } else
            addR(header, value);
    }

    private void addR(Node<T> node, T value) {
        System.out.println("Quiero agregar el : " + value.toString());
        if (node == null) { //Creo que nunca llegaria a este caso
            node = new Node<>(value);
            return;
        } else if (comp.compare(node.getValue(), value) > 0) { //Inserto
            Node<T> aux = new Node<>(value);
            aux.setNext(node);
            node = aux;
            return;
        } else if (node.getNext() == null) {
            node.setNext(new Node<>(value));
            return;
        } else if (node.getNexNext() == null) {
            node.setNexNext(new Node<>(value));
            node.getNext().setNext(node.getNexNext());
            return;
        } else {
            if (comp.compare(node.getNext().getValue(), value) > 0) { //inserto
                Node<T> aux = new Node<>(value);
                aux.setNext(node.getNext());
                aux.setNexNext(aux.getNext().getNext());
                node.setNext(aux);
                node.setNexNext(aux.getNext());
                return;
            } else if (comp.compare(node.getNexNext().getValue(), value) > 0) { //inserto
                Node<T> aux = new Node<>(value);
                aux.setNext(node.getNexNext());
                aux.setNexNext(node.getNexNext().getNext());
                node.setNexNext(aux);
                node.getNext().setNext(aux);
                node.getNext().setNexNext(aux.getNext());
                return;
            } else {
                if (node.getNexNext().getNext() == null) { //si le saco de linea 64 a 69, se pone menos eficiente
                    node.getNexNext().setNext(new Node<>(value)); //Pero sigue dentro de lo pedido
                    node.getNext().setNexNext(node.getNexNext().getNext());
                    return;
                } else
                    addR(node.getNexNext(), value);
            }
        }
    }

    @Override
    public void print() {
        Node<T> aux = header;
        System.out.println("");
        while (aux!= null) {
            System.out.print(aux.getValue().toString() + " ");
            aux = aux.getNext();
        }
    }

    private class Node<T> {
        private T value;
        private Node<T> next = null;
        private Node<T> nexNext = null;

        public Node(T value) {
            this.value = value;
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

        public Node<T> getNexNext() {
            return nexNext;
        }

        public void setNexNext(Node<T> nexNext) {
            this.nexNext = nexNext;
        }
    }
}
/* addR eficiente ( un poco mejor que lo necesario), menos feo que el de abajo
System.out.print("1 "); //Para contar la cantidad de veces que entro
        System.out.println("");
        if (node == null) { //Creo que nunca llegaria a este caso
            node = new Node<>(value);
            return;
        } else if (comp.compare(node.getValue(), value) > 0) { //Inserto
            Node<T> aux = new Node<>(value);
            aux.setNext(node);
            node = aux;
            return;
        } else if (node.getNext() == null) {
            node.setNext(new Node<>(value));
            return;
        } else if (node.getNexNext() == null) {
            node.setNexNext(new Node<>(value));
            node.getNext().setNext(node.getNexNext());
            return;
        } else {
            if (comp.compare(node.getNext().getValue(), value) > 0) { //inserto
                Node<T> aux = new Node<>(value);
                aux.setNext(node.getNext());
                aux.setNexNext(aux.getNext().getNext());
                node.setNext(aux);
                node.setNexNext(aux.getNext());
                return;
            } else if (comp.compare(node.getNexNext().getValue(), value) > 0) { //inserto
                Node<T> aux = new Node<>(value);
                aux.setNext(node.getNexNext());
                aux.setNexNext(node.getNexNext().getNext());
                node.setNexNext(aux);
                node.getNext().setNext(aux);
                node.getNext().setNexNext(aux.getNext());
                return;
            } else {
                if (node.getNexNext().getNext() == null) {
                    node.getNexNext().setNext(new Node<>(value));
                    node.getNext().setNexNext(node.getNexNext().getNext());
                    return;
                } else
                    addR(node.getNexNext(), value);
            }
        }
 */

/* mega eficiente (mejor que lo que piden) --> un asco en cuanto a codigo
System.out.println("Quiero agregar " + value.toString() ); //Para contar la cantidad de veces que entro
        if (node == null) { //Creo que nunca llegaria a este caso
            node = new Node<>(value);
            return;
        } else if (comp.compare(node.getValue(), value) > 0) { //Inserto
            Node<T> aux = new Node<>(value);
            aux.setNext(node);
            node = aux;
            return;
        } else if (node.getNext() == null) {
            node.setNext(new Node<>(value));
            return;
        } else if (node.getNexNext() == null) {
            node.setNexNext(new Node<>(value));
            node.getNext().setNext(node.getNexNext());
            return;
        } else{
            if (comp.compare(node.getNext().getValue(), value) > 0){//tengo que mover el node.getNext a node.getNextNext
                Node<T> aux = new Node<>(value);
                aux.setNext(node.getNext());
                aux.setNexNext(node.getNexNext());
                node.setNext(aux);
                return;
            } else if (comp.compare(node.getNexNext().getValue(), value) > 0){
                //tengo 1->5->7, estoy parado en el 1, y tengo que insertar 6, tengo que mover el puntero de 1.getNextNExt
                //al 6, mover el puntero del 5.getNext al 6, y el puntero del 5.getNextNext al 7, y el 6.getNext al 7, y el
                //6.getNextNext al 7.getNext
                Node<T> aux = new Node<>(value);
                aux.setNext(node.getNexNext());
                aux.setNexNext(aux.getNext().getNext());
                node.getNext().setNext(aux);
                node.getNext().setNexNext(aux.getNext());
                node.setNexNext(aux);
                return;
            } else if (node.getNexNext().getNext() != null && comp.compare(node.getNexNext().getNext().getValue(), value) > 0){
                //tengo 1->5->7->9, estoy parado en el 1, y tengo que meter el 8.
                //Muevo el puntero del 5.getNextNext al 8, muevo el 7.getNext al 8
                //Muevo el 7.getNextNext al 9, meto el 8, 8.getNext al 9, y 8.getNextNext al 9.getNext
                Node<T> aux = new Node<>(value);
                aux.setNext(node.getNexNext().getNext());
                aux.setNexNext(node.getNexNext().getNexNext());
                node.getNexNext().setNext(aux);
                node.getNext().setNexNext(aux);
                return;
            } else if (node.getNexNext().getNext() == null){
                   Node<T> aux = new Node<>(value);
                   node.getNexNext().setNext(aux);
                   node.getNext().setNexNext(aux);
                   return;
            } else {
                System.out.println("Llama aca");
                addR(node.getNexNext().getNext(), value);
            }
        }
        return;
 */