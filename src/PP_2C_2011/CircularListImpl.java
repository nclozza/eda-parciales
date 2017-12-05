package P12011;

import java.util.NoSuchElementException;

/**
 * Created by nico on 14/11/17.
 */
public class CircularListImpl<T> implements CircularList<T> {

  private Node<T> first;
  private Node<T> actual;
  private Node<T> last;

  public CircularListImpl() {
    this.first = null;
    this.actual = null;
    this.last = null;
  }

  private CircularListImpl(Node<T> first, Node<T> last) {
    this.first = first;
    this.actual = null;
    this.last = last;
  }

  @Override
  public void addLast(T elem) {
    if (first == null) {
      Node<T> aux = new Node<>(elem);
      aux.next = aux;
      last = aux;
      first = aux;

    } else {
      Node<T> aux = new Node<>(elem, first);
      if (first.next == first) {
        first.next = aux;
      }
      last = aux;
    }
  }

  @Override
  public T getNext() {
    if (first == null) {
      throw new NoSuchElementException();
    }

    if (actual == null) {
      actual = first;
    }

    Node<T> aux = actual;
    actual = aux.next;
    return aux.element;
  }

  @Override
  public void reset() {
    actual = null;
  }

  @Override
  public CircularList<T> split() {
    if (actual == null) {
      throw new IllegalStateException();
    }

    Node<T> auxActual = actual;
    Node<T> auxLast = last;
    actual.next = first;
    last = actual;
    actual = null;

    return new CircularListImpl<>(auxActual.next, auxLast);
  }


  private static class Node<T> {

    T element;
    Node<T> next;

    Node(T element) {
      this.element = element;
      this.next = null;
    }

    Node(T element, Node<T> next) {
      this.element = element;
      this.next = next;
    }

  }


}
