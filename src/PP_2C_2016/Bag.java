package PP_2C_2016;

/**
 * Created by nclozza on 04/12/17.
 */
public class Bag<T> implements BagInterface<T> {

  private Node<T> first = null;

  @Override
  public void add(T value) {
    if (first == null) {
      first = new Node<>(value);
      return;
    }

    Node<T> actual = first;
    Node<T> previous = null;

    while (actual != null && !actual.element.equals(value)) {
      previous = actual;
      actual = actual.next;
    }

    if (previous == null) {
      actual.count++;

    } else if (actual == null) {
      previous.next = new Node<>(value);

    } else {
      previous.next = actual.next;
      actual.count++;
      addOrder(actual);
    }
  }

  private void addOrder(Node<T> node) {
    Node<T> actual = first.next;
    Node<T> previous = first;

    while (actual != null && actual.count >= node.count) {
      previous = actual;
      actual = actual.next;
    }

    previous.next = node;
    node.next = actual;
  }

  @Override
  public int occurrencesOf(T value) {
    Node<T> aux = first;

    while (aux != null && !aux.element.equals(value)) {
      aux = aux.next;
    }

    return aux == null ? 0 : aux.count;
  }

  @Override
  public T removeMostPopular() {
    if (first == null) {
      return null;
    }

    Node<T> aux = first;
    first = first.next;

    return aux.element;
  }

  public void print() {
    Node<T> aux = first;
    while (aux != null) {
      System.out.print(aux.element + "(" + aux.count + ") ");
      aux = aux.next;
    }
    System.out.println();
  }

  private static class Node<T> {

    T element;
    Node<T> next;
    int count;

    public Node(T element) {
      this.element = element;
      next = null;
      count = 1;
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
