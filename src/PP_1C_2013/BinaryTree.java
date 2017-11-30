package PP_1C_2013;

import java.util.ArrayList;
import java.util.Collections;
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

    public static <T> void printTree(BinaryTree<T> root) {
        int maxLevel = maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T> void printNodeInternal(List<BinaryTree<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces);

        List<BinaryTree<T>> newNodes = new ArrayList<BinaryTree<T>>();
        for (BinaryTree<T> node : nodes) {
            if (node != null) {
                System.out.print(node.value);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    printWhitespaces(1);

                printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    printWhitespaces(1);

                printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T> int maxLevel(BinaryTree<T> node) {
        if (node == null)
            return 0;

        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}
