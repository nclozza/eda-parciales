package PP_1C_2013;

import java.util.LinkedList;

/**
 * Created by nclozza on 03/12/17.
 */
public class Test {

  public static void main(String[] str) {

    LinkedList<String> list = new LinkedList<>();
    list.add("A");
    list.add("B");
    list.add("C");
    list.add("D");
    list.add("E");
    list.add("F");

    BinaryTree<String> tree = BinaryTree.buildFromList(list);
    BinaryTree.printTree(tree);

  }
}
