package PP_1C_2015;

public class Main {
    public static void main(String[] args){
        BinaryTree<String> t1 = new BinaryTree<>("B", null, null);
        BinaryTree<String> tE = new BinaryTree<>("E", new BinaryTree<>("I", new BinaryTree<>("J", null, null), null), null);
        BinaryTree<String> tD = new BinaryTree<>("D", new BinaryTree<>("F", null, new BinaryTree<>("G", null, null)), t1);
        BinaryTree<String> t2 = new BinaryTree<>("C", tD,tE);
        BinaryTree<String> tree = new BinaryTree<>("A", t2, t1);
        BinaryTree.printTree(tree);

    }
}
