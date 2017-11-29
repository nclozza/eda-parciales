package PP_1C_2011;

public class BinaryTree<T> {

    private T value;
    private BinaryTree<T> left, right;

    public BinaryTree(T value, BinaryTree<T> left, BinaryTree<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public T getValue() {
        return value;
    }
    public BinaryTree<T> getLeft() {
        return left;
    }
    public BinaryTree<T> getRight() {
        return right;
    }
}

