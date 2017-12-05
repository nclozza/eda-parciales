package SR_2C_2017;

/**
 * Created by nclozza on 05/12/17.
 */
public class PrintCombination {

  /**
   *  Given a number N print the first solution of a boolean array that satisfies the method evaluate
   *  This method, evaluate, it's not visible, so you must try all the possible combinations until get the correct one
   */
  public static void printCombination(int n) {
    boolean[] vec = new boolean[n];

    if (printCombinationR(vec, 0)) {
      for (int i = 0; i < vec.length; i++) {
        if (vec[i]) {
          System.out.print("1 ");
        } else {
          System.out.print("0 ");
        }
      }
    }
  }

  private static boolean printCombinationR(boolean[] vec, int index) {
    if (evaluate(vec)) {
      return true;
    }

    for (int i = index; i < vec.length; i++) {
      vec[i] = !vec[i];
      if (printCombinationR(vec, i + 1)) {
        return true;
      }
      vec[i] = !vec[i];
    }

    return false;
  }

  private static boolean evaluate(boolean[] vec) {
    return !vec[0] && !vec[1] && vec[2] && !vec[3];
  }
}
