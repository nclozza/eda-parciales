package PP_1C_2015;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by nclozza on 04/12/17.
 */
public class BinaryTree<T> {

  private T value;
  private BinaryTree<T> left, right;

  public BinaryTree(T value, BinaryTree<T> left, BinaryTree<T> right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  public static <T> void printTree(BinaryTree<T> root) {
    int maxLevel = maxLevel(root);

    printNodeInternal(Collections.singletonList(root), 1, maxLevel);
  }

  private static <T> void printNodeInternal(List<BinaryTree<T>> nodes, int level, int maxLevel) {
    if (nodes.isEmpty() || isAllElementsNull(nodes))
      return;

    int floor = maxLevel - level;
    int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
    int firstSpaces = (int) Math.pow(2, (floor)) - 1;
    int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

    printWhitespaces(firstSpaces);

    List<BinaryTree<T>> newNodes = new ArrayList<BinaryTree<T>>();
    for (BinaryTree<T> node : nodes) {
      if (node != null) {
        System.out.print(node.value);
        newNodes.add(node.left);
        newNodes.add(node.right);
      } else {
        newNodes.add(null);
        newNodes.add(null);
        System.out.print(" ");
      }

      printWhitespaces(betweenSpaces);
    }
    System.out.println("");

    for (int i = 1; i <= endgeLines; i++) {
      for (int j = 0; j < nodes.size(); j++) {
        printWhitespaces(firstSpaces - i);
        if (nodes.get(j) == null) {
          printWhitespaces(endgeLines + endgeLines + i + 1);
          continue;
        }

        if (nodes.get(j).left != null)
          System.out.print("/");
        else
          printWhitespaces(1);

        printWhitespaces(i + i - 1);

        if (nodes.get(j).right != null)
          System.out.print("\\");
        else
          printWhitespaces(1);

        printWhitespaces(endgeLines + endgeLines - i);
      }

      System.out.println("");
    }

    printNodeInternal(newNodes, level + 1, maxLevel);
  }

  private static void printWhitespaces(int count) {
    for (int i = 0; i < count; i++)
      System.out.print(" ");
  }

  private static <T> int maxLevel(BinaryTree<T> node) {
    if (node == null)
      return 0;

    return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
  }

  private static <T> boolean isAllElementsNull(List<T> list) {
    for (Object object : list) {
      if (object != null)
        return false;
    }

    return true;
  }

  public static <T> int longestPathLength(BinaryTree<T> tree) {
    /*  I'm counting the nodes, so i must subtract 1
        The path length between two nodes is the total nodes - 1 */
    return longestPathLengthR(tree, new Height()) - 1;
  }

  private static <T> int longestPathLengthR(BinaryTree<T> tree, Height height) {
    if (tree == null) {
      height.h = 0;
      return 0; /* diameter is also 0 */
    }

    /* lh --> Height of left subtree
       rh --> Height of right subtree */
    Height lh = new Height();
    Height rh = new Height();
    lh.h++;
    rh.h++;

    /* ldiameter  --> diameter of left subtree
       rdiameter  --> Diameter of right subtree */
    /* Get the heights of left and right subtrees in lh and rh
     And store the returned values in ldiameter and ldiameter */
    int ldiameter = longestPathLengthR(tree.left, lh);
    int rdiameter = longestPathLengthR(tree.right, rh);

    /* Height of current node is max of heights of left and
     right subtrees plus 1*/
    height.h = Math.max(lh.h, rh.h) + 1;

    return Math.max(lh.h + rh.h + 1, Math.max(ldiameter, rdiameter));
  }

  private static class Height {
    int h;
  }
}
