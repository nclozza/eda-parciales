package PP_1C_2013;

import java.util.Comparator;

public class Main {
    public static void main(String[] args){
        MySortedFastList<Integer> list = new MySortedFastList<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return integer - t1;
            }
        });
        list.add(1);
        list.add(5);
        list.add(7);
        list.add(9);
        list.add(8);
        list.print();
    }
}
