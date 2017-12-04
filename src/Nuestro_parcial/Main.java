package Nuestro_parcial;

import java.util.Random;

public class Main {
    public static void main(String[] args){
//        Graph g = new Graph();
//        g.addVertex(1.0);
//        g.addVertex(2.0);
//        g.addVertex(-2.0);
//        g.addVertex(4.0);
//        g.addVertex(6.0);
//        g.addVertex(3.0);
//        g.addVertex(-1.0);
//        g.addArc(1.0, 2.0);
//        g.addArc(2.0, -2.0);
//        g.addArc(2.0, 4.0);
//        g.addArc(-2.0, 6.0);
//        g.addArc(6.0, 3.0);
//        g.addArc(3.0, -1.0);
//        System.out.println(g.pathForSum(7, 1.0));

        boolean[] ret = new boolean[6];
        int count = 0;
        Random random = new Random();
        while (count < 6){
            int r = (int) random.nextInt(6);
            if (!ret[r]){
                ret[r] = true;
                count++;
            }
        }

        for(int i =0; i < ret.length ; i++)
            System.out.print(ret[i] + " ");


    }
}
