package SP_2C_2017_bis;

import java.awt.*;

/**
 * Created by nclozza on 03/12/17.
 */
public class Test {
  
  public static void main(String[] str) {

//    char board[][] = {
//        {' ', ' ', ' ', ' ', ' ', ' '},
//        {' ', ' ', ' ', 'R', ' ', ' '},
//        {' ', ' ', ' ', 'R', 'G', ' '},
//        {' ', ' ', ' ', 'R', ' ', ' '},
//        {' ', ' ', ' ', ' ', ' ', ' '},
//        {' ', ' ', ' ', ' ', ' ', ' '},
//      };
//
//      Point position = new Point(0, 0);
//
//      System.out.println(GoldFinder.canFindGold(board, position, 6));

    Graph<String> g = new Graph<>();

    g.addVertex("A");
    g.addVertex("B");
    g.addVertex("C");
    g.addVertex("D");
    g.addVertex("E");
    g.addVertex("F");

    g.addArc("A", "B", 2);
    g.addArc("B", "C", 7);
    g.addArc("B", "D", 4);
    g.addArc("B", "E", 1);
    g.addArc("C", "D", 8);
    g.addArc("D", "E", 5);
    g.addArc("E", "F", 3);

    System.out.println(g.isPath(11));

  }
}
