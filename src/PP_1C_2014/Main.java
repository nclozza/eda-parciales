package PP_1C_2014;

import java.util.Comparator;

public class Main {
    public static void main(String[] args){
//        BinaryTree<Integer> t1 = new BinaryTree<>(3, new BinaryTree<>(1, null, null), new BinaryTree<>(2, null, null));
//        BinaryTree<Integer> tree = new BinaryTree<>(7, t1, new BinaryTree<>(6, new BinaryTree<>(4, null, null), new BinaryTree<>(5, null, null)));
//        System.out.println(tree.isPostOrderSorted(tree, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer integer, Integer t1) {
//                return integer-t1;
//            }
//        }));

        MyCustomMap<String, String> m = new MyCustomMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");
        m.put("k3", "v3");
        System.out.println(m.get("k2"));
        System.out.println(m.getMostAccessed());
        System.out.println(m.get("k1"));
        System.out.println(m.get("k2"));
        m.removeLeastAccessed();
        m.removeLeastAccessed();
        m.put("k4", "v4");
        m.put("k4", "v5");
        m.put("k4", "v6");
        System.out.println(m.get("k4"));
        System.out.println(m.getMostAccessed());
        m.removeLeastAccessed();
        m.removeLeastAccessed();

    }
}
