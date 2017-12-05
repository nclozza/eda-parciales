package PP_2C_2011;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nico on 14/11/17.
 */
public class Test {

  public static void main(String[] str) {

//    BinaryTree<String> treeD = new BinaryTree<>("D", null, null);
//    BinaryTree<String> treeE = new BinaryTree<>("E", null, null);
//    BinaryTree<String> treeB = new BinaryTree<>("B", treeD, treeE);
//    BinaryTree<String> treeF = new BinaryTree<>("F", null, null);
//    BinaryTree<String> treeC = new BinaryTree<>("C", null, treeF);
//    BinaryTree<String> treeA = new BinaryTree<>("A", treeB, treeC);
//
//    List<String> list = new LinkedList<>();
//    list.add("D");
//    list.add("E");
//    list.add("B");
//    list.add("F");
//    list.add("C");
//    list.add("A");
//
//    System.out.println(BinaryTree.checkPostorder(treeA, list));
//
//    list.clear();
//
//    list.add("D");
//    list.add("E");
//    list.add("B");
//    list.add("F");
//    list.add("A");
//    list.add("C");
//
//    System.out.println(BinaryTree.checkPostorder(treeA, list));

    CircularList<String> circularList = new CircularListImpl<>();

    circularList.addLast("A");
    circularList.addLast("B");
    circularList.addLast("C");
    System.out.println(circularList.getNext());
    System.out.println(circularList.getNext());
    System.out.println(circularList.getNext());
  }

}
