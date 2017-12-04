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

    public static <T> int height(BinaryTree<T> tree){
        if (tree == null)
            return 0;
        return heightR(tree);
    }

    private static <T> int heightR(BinaryTree<T> tree) {
        if(tree == null)
            return 0;
        return 1 + Math.max(heightR(tree.left), heightR(tree.right));
    }

    public static <T> int longestPath(BinaryTree<T> tree){
        if (tree == null)
            throw new IllegalArgumentException();
        Pack p = new Pack();
        longestR(tree, p);
        System.out.println("p.maxS " +p.maxS);
        System.out.println("p.maxH " +p.maxH);

        return p.maxH > p.maxS ? p.maxH : p.maxS;

    }

    private static <T> void longestR(BinaryTree<T> tree, Pack p) {

        if (tree.left != null && tree.right != null){
            if (p.maxH== 0)
                p.maxH+=2;
            else
                p.maxH += 1;
            if(!p.b) {
                p.b = true;
                p.maxS += 2;
            }
            else {
                p.maxS -= 2;
            }

            p.b = false;
                longestR(tree.left, p);
            longestR(tree.right, p);
        } else if (tree.right != null){
            p.maxH+=1;
//            p.maxS+=1;
            longestR(tree.right, p);
        } else if (tree.left != null){
//            p.maxS+=1;
            p.maxH+=1;
        } else {
            return;
        }
    }

    private static class Pack{
        int maxH = 0;
        int maxS = 0;
        boolean b= false;

        public int getMaxH() {
            return maxH;
        }

        public void setMaxH(int maxH) {
            this.maxH = maxH;
        }

        public int getMaxS() {
            return maxS;
        }

        public void setMaxS(int maxS) {
            this.maxS = maxS;
        }
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
