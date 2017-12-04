package PP_2C_2016;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by nclozza on 04/12/17.
 */
public class BinarySearchTree<T extends Comparable> {
  private Node<T> root;

  public BinarySearchTree() {
    root = null;
  }

  /**
   * Agrega una clave al árbol.
   * Si ya existe, no hace nada y el árbol no se modifica.
   */
  public void add(T value) {
    root = addR(value, root);
  }

  private Node<T> addR(T value, Node<T> node) {
    if (node == null) {
      return new Node<>(value);
    } else {
      int aux = value.compareTo(node.value);

      if (aux < 0) {
        node.leftChild = addR(value, node.leftChild);

      } else if (aux > 0) {
        node.rightChild = addR(value, node.rightChild);
      }

      return node;
    }
  }

  /**
   * Elimina una clave del árbol.
   * Si no existe, no hace nada y el árbol no se modifica.
   */
  public void remove(T value) {
    root = removeR(value, root);
  }

  private Node<T> removeR(T value, Node<T> node) {
    if (node == null) {
      return null;
    }

    int compare = value.compareTo(node.value);

    if (compare < 0) {
      node.leftChild = removeR(value, node.leftChild);

    } else if (compare > 0) {
      node.rightChild = removeR(value, node.rightChild);

    } else {
      if (node.leftChild == null) {
        return node.rightChild;

      } else if (node.rightChild == null) {
        return node.leftChild;

      } else {
        Node<T> aux = node.leftChild;

        while (aux.rightChild != null) {
          aux = aux.rightChild;
        }

        node.value = aux.value;
        node.leftChild = removeR(node.value, node.leftChild);
      }
    }

    return node;
  }


  /**
   * Determina si el árbol contiene o no una clave.
   */
  public boolean contains(T value) {
    return containsR(value, root);
  }

  private boolean containsR(T value, Node<T> node) {
    if (node == null) {
      return false;
    }

    int compare = value.compareTo(node.value);

    if (compare < 0) {
      return containsR(value, node.leftChild);
    } else if (compare > 0) {
      return containsR(value, node.rightChild);
    } else {
      return true;
    }
  }

  /**
   * Retorna la cantidad de claves almacenadas.
   */
  public int size() {
    return sizeR(root);
  }

  private int sizeR(Node<T> node) {
    if (node != null) {
      return 1 + sizeR(node.leftChild) + sizeR(node.rightChild);
    } else {
      return 0;
    }
  }

  public int valueLevel(T value) {
    return valueLevelR(value, root, 0);
  }

  private int valueLevelR(T value, Node<T> node, int level) {
    if (node == null) {
      return -1;
    }

    int compare = value.compareTo(node.value);

    if (compare < 0) {
      return valueLevelR(value, node.leftChild, ++level);
    } else if (compare > 0) {
      return valueLevelR(value, node.rightChild, ++level);
    } else {
      return level;
    }
  }

  public int totalLeaves() {
    int totalLeaves = 0;
    if (root == null) {
      return totalLeaves;
    } else {
      return totalLeavesR(root, totalLeaves);
    }
  }

  private int totalLeavesR(Node<T> node, int totalLeaves) {
    if (node == null) {
      return 0;

    } else if (node.leftChild == null && node.rightChild == null) {
      return 1;

    } else {
      return totalLeaves + totalLeavesR(node.leftChild, totalLeaves) + totalLeavesR(node.rightChild, totalLeaves);
    }
  }

  public T maxValue() {
    Node<T> auxNode = root;
    T auxValue = null;
    while (auxNode != null) {
      auxValue = auxNode.value;
      auxNode = auxNode.rightChild;
    }

    return auxValue;
  }

  public void printPredecessors(T value) {
    printPredecessorsR(value, root);
  }

  private boolean printPredecessorsR(T value, Node<T> node) {
    if (node == null) {
      return false;
    }

    int compare = value.compareTo(node.value);

    if (compare < 0) {
      if (printPredecessorsR(value, node.leftChild)) {
        System.out.println(node.value);
      }
    } else if (compare > 0) {
      if (printPredecessorsR(value, node.rightChild)) {
        System.out.println(node.value);
      }
    }

    return true;
  }


  public void printTree() {
    Queue<Node<T>> currentLevel = new LinkedList<>();
    Queue<Node<T>> nextLevel = new LinkedList<>();

    currentLevel.add(root);

    while (!currentLevel.isEmpty()) {
      Iterator<Node<T>> iter = currentLevel.iterator();

      while (iter.hasNext()) {
        Node<T> currentNode = iter.next();
        if (currentNode.leftChild != null) {
          nextLevel.add(currentNode.leftChild);
        }
        if (currentNode.rightChild != null) {
          nextLevel.add(currentNode.rightChild);
        }
        System.out.print(currentNode.value + " ");
      }

      System.out.println();
      currentLevel = nextLevel;
      nextLevel = new LinkedList<>();
    }
  }

  private static class Node<T> {
    private T value;
    private Node<T> leftChild;
    private Node<T> rightChild;

    Node(T value) {
      this.value = value;
      leftChild = null;
      rightChild = null;
    }
  }

  public boolean subTree(BinarySearchTree<T> secondTree) {
    Node<T> auxNodeTree = getOccurence(root, secondTree.root);

    if (auxNodeTree == null) {
      return false;
    }

    return checkSubTree(auxNodeTree, secondTree.root);
  }

  private boolean checkSubTree(Node<T> node, Node<T> secondNode) {
    if (node == null && secondNode == null) {
      return true;

    } else if (node == null || secondNode == null) {
      return false;
    }

    if (node.value.equals(secondNode.value)) {
      return checkSubTree(node.leftChild, secondNode.leftChild)
                && checkSubTree(node.rightChild, secondNode.rightChild);

    } else {
      return false;
    }
  }

  private Node<T> getOccurence(Node<T> node, Node<T> secondNode) {
    if (node == null) {
      return null;
    }

    int cmp = secondNode.value.compareTo(node.value);
    if (cmp == 0) {
      return node;

    } else if (cmp < 0) {
      return getOccurence(node.leftChild, secondNode);

    } else {
      return getOccurence(node.rightChild, secondNode);
    }
  }
}
