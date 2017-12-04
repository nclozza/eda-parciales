package PP_2C_2016;

/**
 * Created by nclozza on 04/12/17.
 */
public class Test {

  public static void main(String[] str) {

//    BinarySearchTree<String> tree = new BinarySearchTree<>();
//    tree.add("F");
//    tree.add("C");
//    tree.add("H");
//    tree.add("A");
//    tree.add("D");
//    tree.add("G");
//    tree.add("I");
//    tree.add("B");
//    tree.add("E");
//    tree.add("J");
//
//    BinarySearchTree<String> secondTree = new BinarySearchTree<>();
//    secondTree.add("C");
//    secondTree.add("A");
//    secondTree.add("D");
//    secondTree.add("B");
//    secondTree.add("E");
//
//    System.out.println(tree.subTree(secondTree));


    Bag<String> bag = new Bag<>();
    bag.add("A");
    bag.add("A");
    bag.add("B");
    bag.add("B");
    bag.add("B");
    bag.add("A");

    bag.print();
    bag.removeMostPopular();
    bag.print();
  }
}
