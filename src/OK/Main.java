package OK;

import sun.awt.image.ImageWatched;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args){
//        Graph<String> g = new Graph<>();
//        g.addVertex("A", 1);
//        g.addVertex("B", 3);
//        g.addVertex("C", 1);
//        g.addVertex("D", 1);
//        g.addVertex("E", 1);
//        g.addArc("B", "C", 1);
//        g.addArc("A", "C", 1);
//        g.addArc("B", "D", 2);
//        g.addArc("C", "D", 10);
//        System.out.println(g.maxPathIterative("A","B"));
//        System.out.println(g.minHamiltonianPath());
        int[] arr = {1,2};
//        for(int i = 0 ; i < arr.length ; i++)
//            System.out.print(arr[i]+" ");
//        System.out.println("");
//        permute(arr, 0);

        allPosible(arr, 5);
    }


    public static void permute(int[] intArray, int start) {
        for(int i = start; i < intArray.length; i++){
            int temp = intArray[start];
            intArray[start] = intArray[i];
            intArray[i] = temp;
            permute(intArray, start + 1);
            intArray[i] = intArray[start];
            intArray[start] = temp;
        }
        if (start == intArray.length - 1) {
            System.out.println(java.util.Arrays.toString(intArray));
        }
    }

    public static void allPosible(int[] arr, int n){
        int start = 0;
        Set<LinkedList<Integer>> comb = new TreeSet<>(new Comparator<LinkedList<Integer>>() {
            @Override
            public int compare(LinkedList<Integer> o1, LinkedList<Integer> o2) {
                return Integer.compare(o1.hashCode(), o2.hashCode());
            }
        });
        for(int i = 0 ; i < arr.length ; i++) {
            System.out.println("llamo");
            LinkedList<Integer> l = new LinkedList<Integer>();
            l.add(arr[i]);
            allR(arr, n, i, l , comb);
            System.out.println("");
        }
        System.out.println(comb.size());


        for(LinkedList<Integer> l : comb){
            for(Integer i : l)
                System.out.print( i +" ");
            System.out.println("");
        }
    }

    private static void allR(int[] arr, int n, int start, LinkedList<Integer> l, Set<LinkedList<Integer>> comb) {

        for(int i = 0 ; i < arr.length ;){

        }
    }

    private static void print(LinkedList<Integer> l) {
        for(Integer i : l)
            System.out.print(i + " ");
        System.out.println("");
    }

    public static int suma(LinkedList<Integer> l){
        int aux = 0;
        for(Integer i : l){
            aux+=i;
        }
        return aux;
    }
}
