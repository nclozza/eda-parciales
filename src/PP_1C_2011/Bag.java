package PP_1C_2011;

/**
 * Created by nclozza on 29/11/17.
 */
public class Bag<T> implements BagInterface<T> {

  private Node<T> first = null;

  @Override
  public void add(T elem) {
    if (first == null) {
      first = new Node<>(elem);
      return;
    }

    Node<T> aux = first;
    Node<T> auxPrevious = null;
    while (!(aux == null || aux.element.equals(elem))) {
      auxPrevious = aux;
      aux = aux.next;
    }

    if (aux == null) {
      auxPrevious.next = new Node<>(elem);

    } else {
      aux.cant++;
      if (auxPrevious != null) {
        auxPrevious.next = aux.next;
        addOrder(aux);
      }
    }
  }

  @Override
  public void remove(T elem) {
    Node<T> aux = first;
    Node<T> auxPrevious = null;
    while (!(aux == null || aux.element.equals(elem))) {
      auxPrevious = aux;
      aux = aux.next;
    }

    if (aux != null) {
      if (aux.cant > 1) {
        aux.cant--;
        if (auxPrevious == null) {
          first = aux.next;

        } else {
          auxPrevious.next = aux.next;
        }

        addOrder(aux);
      } else {
        if (auxPrevious == null) {
          first = first.next;
        } else {
          auxPrevious.next = aux.next;
        }
      }
    }
  }

  @Override
  public void print() {
    Node<T> aux = first;
    while (aux != null) {
      System.out.print(aux.element + "(" + aux.cant + ") ");
      aux = aux.next;
    }
    System.out.println();
  }

  private void addOrder(Node<T> node) {
    Node<T> aux = first;
    Node<T> auxPrevious = null;
    while (aux != null && node.cant <= aux.cant) {
      auxPrevious = aux;
      aux = aux.next;
    }

    if (auxPrevious == null) {
      node.next = first;
      first = node;

    } else {
      auxPrevious.next = node;
      node.next = aux;
    }
  }


  private static class Node<T> {

    T element;
    int cant;
    Node next;

    public Node(T element) {
      this.element = element;
      cant = 1;
      next = null;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      final Node<T> other = (Node) obj;
      return element.equals(other.element);
    }
  }
}
