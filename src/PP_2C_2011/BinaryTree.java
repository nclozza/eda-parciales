package PP_2C_2011;

import java.util.List;

/**
 * Created by nico on 14/11/17.
 */
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

  public static <T> boolean checkPostorder(BinaryTree<T> tree, List<T> values) {
    if (tree == null) {
      if (values.isEmpty()) {
        return true;
      } else {
        return false;
      }
    }

    return checkPostorderR(tree, values, new Package());
  }


  private static <T> boolean checkPostorderR(BinaryTree<T> tree, List<T> values, Package pack) {
    if (tree.left == null && tree.right == null && values.get(pack.listIndex).equals(tree.value)) {
      return true;

    } else if (tree.left == null) {
      if (checkPostorderR(tree.right, values, pack)) {
        pack.addIndex();
        return tree.value.equals(values.get(pack.listIndex));
      }
      return false;

    } else if (tree.right == null) {
      if (checkPostorderR(tree.left, values, pack)) {
        pack.addIndex();
        return tree.value.equals(values.get(pack.listIndex));
      }
      return false;

    } else {
      if (checkPostorderR(tree.left, values, pack)) {
        pack.addIndex();
        if (checkPostorderR(tree.right, values, pack)) {
          pack.addIndex();
          return tree.value.equals(values.get(pack.listIndex));
        }

        return false;
      }

      return false;
    }
  }

  private static class Package {
    int listIndex = 0;

    void addIndex() {
      listIndex++;
    }
  }
}