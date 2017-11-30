package PP_1C_2011;

/**
 * Created by nico on 29/11/17.
 */
public class Test {

  public static void main(String[] str) {

    /*
    Bag<String> bag = new Bag<>();

    bag.print(); // No imprime nada.

    bag.add("A");
    bag.add("B");
    bag.add("C");
    bag.add("B");
    bag.print(); // Imprime B (2) C (1) A (1)

    bag.add("A");
    bag.add("C");
    bag.add("C");
    bag.print(); // Imprime C (3) A (2) B (2)

    bag.remove("C");
    bag.add("D");
    bag.print(); // Imprime C (2) A (2) B (2) D (1)

    bag.remove("C");
    bag.print(); // Imprime A (2) B (2) C (1) D (1)

    bag.remove("C");
    bag.print(); // Imprime A (2) B (2) D (1)
    */

    BinaryTree<String> tree1 = new BinaryTree<>("D", null, null);
    BinaryTree<String> tree2 = new BinaryTree<>("E", null, null);
    BinaryTree<String> tree3 = new BinaryTree<>("B", tree1, tree2);
    BinaryTree<String> tree4 = new BinaryTree<>("D", null, null);
    BinaryTree<String> tree5 = new BinaryTree<>("B", tree4, tree3);
    BinaryTree<String> tree6 = new BinaryTree<>("B", null, null);
    BinaryTree<String> tree7 = new BinaryTree<>("C", null, tree6);
    BinaryTree<String> tree8 = new BinaryTree<>("A", tree5, tree7);

    BinaryTree.printTree(tree8);

    BinaryTree<String> tree = BinaryTree.spanning(tree8, "B");
    BinaryTree.printTree(tree);

  }
}
