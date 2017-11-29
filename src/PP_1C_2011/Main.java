package PP_1C_2011;

public class Main {

    //Bag main
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
}
