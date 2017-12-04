package PP_2C_2016;

import java.util.Comparator;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        MyBag<Integer> bag = new MyBag<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });

        for(int i = 0 ; i < 1000 ; i++){
            Random random = new Random();
            bag.add(random.nextInt(3));
        }
        bag.print();
    }
}
