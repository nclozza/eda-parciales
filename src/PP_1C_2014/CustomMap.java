package PP_1C_2014;

/**
 * Created by nclozza on 30/11/17.
 */
public class CustomMap<K, V> implements CustomMapInterface<K, V> {

  private Node<K, V> first = null;
  private Node<K, V> last = null;

  @Override
  public V get(K key) {
    Node<K, V> aux = first;
    while (aux != null && !aux.key.equals(key)) {
      aux = aux.next;
    }

    if (aux == null) {
      return null;
    }

    aux.accessesNumber++;
    return aux.value;
  }

  @Override
  public void put(K key, V value) {
    if (first == null) {
      first = new Node<>(key, value);
      last = first;
      return;
    }

    Node<K, V> aux = first;
    while (aux != null && !aux.key.equals(key)) {
      aux = aux.next;
    }

    if (aux == null) {
      Node<K, V> auxNode = new Node<>(key, value);
      last.next = auxNode;
      auxNode.previous = last;
      last = last.next;

    } else {
      aux.accessesNumber++;
      aux.value = value;
      if (aux != first) {
        aux.previous.next = aux.next;
        if (aux != last) {
          aux.next.previous = aux.previous;

        } else {
          last = last.previous;
        }

        putOrder(aux);
      }
    }
  }

  private void putOrder(Node<K, V> node) {
    Node<K, V> aux = first;
    Node<K, V> auxPrevious = null;

    while (aux != null && node.accessesNumber <= aux.accessesNumber) {
      auxPrevious = aux;
      aux = aux.next;
    }

    if (aux == null) {
      auxPrevious.next = node;
      node.previous = auxPrevious;
      last = node;

    } else {
      if (aux == first) {
        node.next = first;
        first.previous = node;
        first = node;

        if (aux == last) {
          node.previous = last;
          last.next = node;
          last = node;

        } else {
          aux.next.previous = node;
          node.next = aux.next;
        }

      } else {
        aux.previous.next = node;
        node.previous = aux.previous;

        if (aux == last) {
          node.previous = last;
          last.next = node;
          last = node;

        } else {
          aux.next.previous = node;
          node.next = aux.next;
        }
      }
    }
  }

  @Override
  public K getMostAccessed() {
    return first.key;
  }

  @Override
  public void removeLeastAccessed() {
    if (last != null) {
      if (first == last) {
        first = null;
        last = null;

      } else {
        last.previous.next = null;
        last = last.previous;
      }
    }
  }

  private static class Node<K, V> {

    private K key;
    private V value;
    private int accessesNumber;
    private Node<K, V> next;
    private Node<K, V> previous;

    public Node(K key, V value) {
      this.key = key;
      this.value = value;
      accessesNumber = 1;
      next = null;
      previous = null;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      final Node<K, V> other = (Node) obj;
      return key.equals(other.key);
    }
  }


}
