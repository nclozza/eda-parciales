package PP_1C_2015;

/**
 * Created by nclozza on 04/12/17.
 */
public class Test {

  public static void main(String[] str) {
//    WordList list = new WordListImpl();
//
//    System.out.println("-----------------------------------------");
//    list.print();      // no imprime nada
//
//    list.add("AA");
//    list.add("DDD");
//    list.add("F");
//    list.add("EE");
//    list.add("BBB");
//    System.out.println("-----------------------------------------");
//    list.print();      // imprime AA DDD F EE BBB
//
//    list.removeEven();
//    System.out.println("-----------------------------------------");
//    list.print();      // imprime DDD F BBB
//
//    list.add("CC");
//    list.add("H");
//    list.add("GGGG");
//    System.out.println("-----------------------------------------");
//    list.print();      // imprime DDD F BBB CC H GGGG
//
//    list.removeOdd();
//    System.out.println("-----------------------------------------");
//    list.print();      // imprime CC GGGG

    BinaryTree<String> tree1 = new BinaryTree<>("A", null, null);
    BinaryTree<String> tree2 = new BinaryTree<>("D", null, null);
    BinaryTree<String> tree3 = new BinaryTree<>("B", tree2, null);
    BinaryTree<String> tree4 = new BinaryTree<>("C", tree1, tree3);
    BinaryTree<String> tree5 = new BinaryTree<>("E", null, null);
    BinaryTree<String> tree6 = new BinaryTree<>("F", tree4, tree5);

    BinaryTree.printTree(tree6);
    System.out.println(BinaryTree.longestPathLength(tree6));


    tree1 = new BinaryTree<>("I", null, null);
    tree2 = new BinaryTree<>("G", tree1, null);
    tree3 = new BinaryTree<>("A", tree2, null);
    tree4 = new BinaryTree<>("H", null, null);
    tree5 = new BinaryTree<>("F", null, tree4);
    tree6 = new BinaryTree<>("E", null, null);
    BinaryTree<String> tree7 = new BinaryTree<>("B", tree5, tree6);
    BinaryTree<String> tree8 = new BinaryTree<>("C", tree3, tree7);
    BinaryTree<String> tree9 = new BinaryTree<>("D", null, null);
    BinaryTree<String> tree10 = new BinaryTree<>("F", tree8, tree9);

    BinaryTree.printTree(tree10);
    System.out.println(BinaryTree.longestPathLength(tree10));


    tree1 = new BinaryTree<>("G", null, null);
    tree2 = new BinaryTree<>("I", null, null);
    tree3 = new BinaryTree<>("B", tree2, null);
    tree4 = new BinaryTree<>("A", tree1, tree3);
    tree5 = new BinaryTree<>("C", tree4, null);
    tree6 = new BinaryTree<>("F", tree5, null);

    BinaryTree.printTree(tree6);
    System.out.println(BinaryTree.longestPathLength(tree6));
  }
}
