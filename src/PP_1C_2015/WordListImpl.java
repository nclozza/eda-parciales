package PP_1C_2015;

/**
 * Created by nclozza on 04/12/17.
 */
public class WordListImpl implements WordList {

  private Node even = null;
  private Node odd = null;

  @Override
  public void add(String word) {
    if (word.length() % 2 == 0) {
      if (even == null) {
        even = new Node(word);
        return;
      }

      Node aux = even;
      while (aux.next != null) {
        aux = aux.next;
      }

      aux.next = new Node(word);

    } else {
      if (odd == null) {
        odd = new Node(word);
        return;
      }

      Node aux = odd;
      while (aux.next != null) {
        aux = aux.next;
      }

      aux.next = new Node(word);
    }
  }

  @Override
  public void print() {
    Node evenAux = even;
    Node oddAux = odd;

    while (evenAux != null && oddAux != null) {
      if (evenAux.index < oddAux.index) {
        System.out.println(evenAux.word);
        evenAux = evenAux.next;

      } else {
        System.out.println(oddAux.word);
        oddAux = oddAux.next;
      }
    }

    if (evenAux == null) {
      while (oddAux != null) {
        System.out.println(oddAux.word);
        oddAux = oddAux.next;
      }

    } else {
      while (evenAux != null) {
        System.out.println(evenAux.word);
        evenAux = evenAux.next;
      }
    }
  }

  @Override
  public void removeEven() {
    even = null;
  }

  @Override
  public void removeOdd() {
    odd = null;
  }

  private static class Node {

    static int totalIndex;
    private String word;
    Node next;
    private int index;

    Node(String word) {
      this.word = word;
      next = null;
      index = totalIndex++;
    }
  }
}
