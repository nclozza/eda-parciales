package PP_1C_2013;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Main {
//    public static void main(String[] args){
//        MySortedFastList<Integer> list = new MySortedFastList<Integer>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer integer, Integer t1) {
//                return integer - t1;
//            }
//        });
//        list.add(1);
//        list.add(5);
//        list.add(7);
//        list.add(9);
//        list.add(8);
//        list.print();
//    }

    public static void main(String[] args){
        List<String> list = new LinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        BinaryTree<String> t = BinaryTree.buildFromList(list);
        t.printInorder(t);
    }
}
