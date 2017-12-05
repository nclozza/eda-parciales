package SR_2C_2017;

/**
 * Created by nclozza on 05/12/17.
 */
public class Test {

  public static void main(String[] str) {

    //    BinaryTree<String> tree1 = new BinaryTree<>("A", null, null);
//    BinaryTree<String> tree2 = new BinaryTree<>("B", null, null);
//    BinaryTree<String> tree3 = new BinaryTree<>("C", tree1, tree2);
//    BinaryTree<String> tree4 = new BinaryTree<>("D", null, null);
//    BinaryTree<String> tree5 = new BinaryTree<>("E", tree3, tree4);
//
//    BinaryTree.printTree(tree5);
//    System.out.println(BinaryTree.sameBranch(tree5, "E", "B"));


//    PrintCombination.printCombination(4);


    Graph<String> g = new Graph<>();

    g.addVertex("A");
    g.addVertex("B");
    g.addVertex("C");
    g.addVertex("D");
    g.addVertex("E");
    g.addVertex("F");

    g.addArc("A", "B", 2);
    g.addArc("B", "C", 7);
    g.addArc("B", "D", 4);
    g.addArc("B", "E", 1);
    g.addArc("C", "D", 8);
    g.addArc("D", "E", 5);
    g.addArc("E", "F", 3);

    System.out.println(g.pathForAll("A", 10));

  }
}
