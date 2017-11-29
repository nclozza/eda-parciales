package PP_1C_2014;

import java.util.Comparator;

public class BinaryTree<T> {

    private T value;
    private BinaryTree<T> left;
    private BinaryTree<T> right;

    public BinaryTree(T value, BinaryTree<T> left, BinaryTree<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public <T> boolean isPostOrderSorted(BinaryTree<T> tree, Comparator<T> c){
        if (tree == null || c == null)
            throw new IllegalArgumentException("ILLEGAL");
        return isPostOR(tree, c);
    }

    private <T> boolean isPostOR(BinaryTree<T> tree, Comparator<T> c) {
        if (tree == null)
            return true;
        if (tree.right != null && tree.left != null){
            if (c.compare(tree.value, tree.right.value) > 0)
                if (c.compare((tree.right.value), tree.left.value) > 0)
                    return true && isPostOR(tree.right, c) && isPostOR(tree.left, c);
            return false;
        } else if (tree.right == null && tree.left != null){
            return c.compare(tree.value, tree.left.value) > 0 && isPostOR(tree.left, c);
        } else if (tree.right != null)
            return c.compare(tree.value, tree.right.value) > 0 && isPostOR(tree.right, c);
        else
            return true;
    }
}
