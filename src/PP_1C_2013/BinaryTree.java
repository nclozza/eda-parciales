package PP_1C_2013;

import java.util.List;

public class BinaryTree<T> {
    private T value;
    private BinaryTree<T> left;
    private BinaryTree<T> right;

    public BinaryTree(T value, BinaryTree<T> left, BinaryTree<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public static <T> BinaryTree<T> buildFromList(List<T> values) {
        if (values == null)
            throw new IllegalArgumentException("ILLEGAL");
        BinaryTree<T> ret = null;
        ret = buildR(ret, values, 0, true);
        return ret;
    }

    private static <T> BinaryTree<T> buildR(BinaryTree<T> ret, List<T> values, int i, boolean completeLevel) {
        if (i >= values.size())
            return ret;
        else{
            if (ret == null){
                ret = new BinaryTree<>(values.get(i), null, null);
            }
            if (i+i+2 < values.size()){
                ret.right = new BinaryTree<>((values.get(i+i+2)), null, null);
            }
            if (i+i+1 < values.size()){
                ret.left = new BinaryTree<>((values.get(i+i+1)), null, null);
            }
            if(!completeLevel)
                return ret;
            ret.left = buildR(ret.left, values, i+i+1, false);
            ret.right = buildR(ret.right, values, i+i+2, true);
        }
        return ret;
    }

    void printPreorder(BinaryTree<T> node)
    {
        if (node == null)
            return;

        /* first print data of node */
        System.out.print(node.value.toString() + " ");

        /* then recur on left sutree */
        printPreorder(node.left);

        /* now recur on right subtree */
        printPreorder(node.right);
    }

    void printInorder(BinaryTree node)
    {
        if (node == null)
            return;

        /* first recur on left child */
        printInorder(node.left);

        /* then print the data of node */
        System.out.print(node.value.toString() + " ");

        /* now recur on right child */
        printInorder(node.right);
    }

}
