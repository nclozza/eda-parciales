package PP_1C_2011;

import java.util.Comparator;

public class Main {

//    Bag main
    public static void main(String[] args){
        Bag<String> bag = new MyBag<>();
        bag.add("A");
        bag.add("B");
        bag.add("C");
        bag.add("C");
        bag.add("B");
        bag.add("B");
        bag.add("C");
        bag.add("C");
        bag.print();
        bag.remove("B");
        bag.print();
    }

    /*//HEAP MAIN

    public static void main(String[] args){
        BinaryTree<Integer> t1 = new BinaryTree<>(3, new BinaryTree<>(9, null, null), new BinaryTree<>(6, null, null));
        BinaryTree<Integer> t2 = new BinaryTree<>(5, null, null);
        BinaryTree<Integer> tree = new BinaryTree<>(1, t1, t2);
        Ejercicio3<Integer> ej = new Ejercicio3<>();
        System.out.println(ej.isHeap(tree, new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return integer - t1;
            }
        }));
    }*/

}
