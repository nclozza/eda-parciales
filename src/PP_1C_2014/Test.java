package PP_1C_2014;

import java.util.Comparator;

/**
 * Created by nclozza on 30/11/17.
 */
public class Test {

  public static void main(String[] str) {

    /*
    CustomMap<String, String> m = new CustomMap<>();
    System.out.println(m.get("k1"));
    m.put("k1", "v1");
    m.put("k2", "v2");
    m.put("k3", "v3");
    System.out.println(m.get("k2"));
    System.out.println(m.getMostAccessed());
    System.out.println(m.get("k1"));
    System.out.println(m.get("k2"));
    m.removeLeastAccessed();
    m.removeLeastAccessed();
    m.put("k4", "v4");
    m.put("k4", "v5");
    m.put("k4", "v6");
    System.out.println(m.get("k4"));
    System.out.println(m.getMostAccessed());
    m.removeLeastAccessed();
    m.removeLeastAccessed();
    System.out.println(m.get("k4"));
    */

    BinaryTree<String> tree1 = new BinaryTree<>("A", null, null);
    BinaryTree<String> tree2 = new BinaryTree<>("B", null, null);
    BinaryTree<String> tree3 = new BinaryTree<>("C", tree1, tree2);
    BinaryTree<String> tree4 = new BinaryTree<>("D", null, null);
    BinaryTree<String> tree5 = new BinaryTree<>("E", tree3, tree4);

    BinaryTree.printTree(tree5);
    System.out.println(BinaryTree.isPostOrderSorted(tree5, Comparator.naturalOrder()));

    tree1 = new BinaryTree<>("A", null, null);
    tree2 = new BinaryTree<>("C", null, null);
    tree3 = new BinaryTree<>("B", tree1, tree2);
    tree4 = new BinaryTree<>("D", null, null);
    tree5 = new BinaryTree<>("E", tree3, tree4);

    BinaryTree.printTree(tree5);
    System.out.println(BinaryTree.isPostOrderSorted(tree5, Comparator.naturalOrder()));

    tree1 = new BinaryTree<>("A", null, null);
    tree2 = new BinaryTree<>("B", null, null);
    tree3 = new BinaryTree<>("C", tree1, tree2);
    tree4 = new BinaryTree<>("E", null, null);
    tree5 = new BinaryTree<>("D", null, tree4);
    BinaryTree<String> tree6 = new BinaryTree<>("D", tree3, tree5);

    BinaryTree.printTree(tree6);
    System.out.println(BinaryTree.isPostOrderSorted(tree6, Comparator.naturalOrder()));
  }
}
