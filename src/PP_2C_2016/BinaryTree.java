package PP_2C_2016;

import java.util.Comparator;

public class BinaryTree<T> {
    private Node root;
    private Comparator<? super T> cmp;

    public BinaryTree(Comparator<? super T> cmp){
        this.root = null;
        this.cmp = cmp;
    }

    public void add (T value){
        //
    }

    public boolean isSubtree(BinaryTree<T> tree){
        if (tree == null)
            throw new IllegalArgumentException();
        return isR(root, tree.root);
    }

    private boolean isR(Node root, Node tree) {
        if(root == null && tree == null)
            return true;
        if (root == null || tree == null)
            return false;
        if (root.value.equals(tree.value)){
            if(tree.left != null && root.left != null && tree.left.value.equals(root.left.value)){
                if(tree.right != null && root.right != null && tree.right.value.equals(root.right.value)){
                    return true && isR(root.left, tree.left) && isR(root.right, tree.right);
                } else if(tree.right == null && root.right != null)
                    return false;
                else if(tree.right == null && root.right == null)
                    return true;
                else
                    return false;
            } else if(tree.left == null && root.left != null)
                return false;
            else if(tree.left == null && root.left == null)
                return true;
            else
                return false;
        } else{
            return isR(root.left, tree) || isR(root.right, tree);
        }
    }

    private class Node{
        T value;
        Node left;
        Node right;
    }
}
