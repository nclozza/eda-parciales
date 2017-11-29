package PP_1C_2011;

import java.util.Comparator;

public class Ejercicio3<T> {

    public boolean isHeap(BinaryTree<T> tree, Comparator<T> comp){
        if (tree == null || comp == null)
            throw new IllegalArgumentException("ILLEGAL");
        int aux = checkHeight(tree);
        return (aux == 0 || aux == 1 || aux == -1) && isHeapC(tree, comp);
    }

    private boolean isHeapC(BinaryTree<T> tree, Comparator<T> comp) {
        if (tree == null)
            return true;
        if (tree.getLeft() != null && tree.getRight() != null) {
            if (comp.compare(tree.getValue(), tree.getLeft().getValue()) > 0 || comp.compare(tree.getValue(), tree.getRight().getValue()) > 0) {
                return false;
            }
        } else if (tree.getLeft() != null && tree.getRight() == null) {
            if (comp.compare(tree.getValue(), tree.getLeft().getValue()) > 0){
                return false;
            }
        } else if(tree.getLeft() == null && tree.getRight() != null){
            return false;
        }

        return isHeapC(tree.getLeft(), comp) && isHeapC(tree.getRight(), comp) && true;
    }

    public int checkHeight(BinaryTree<T> tree){
        return checkHeightR(tree.getLeft()) - checkHeightR(tree.getRight());
    }

    private int checkHeightR(BinaryTree<T> tree) {
        if (tree != null)
            return 1 + Math.max(checkHeightR(tree.getLeft()), checkHeightR(tree.getRight()));
        return -1;
    }

}
