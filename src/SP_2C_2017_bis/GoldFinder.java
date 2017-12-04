package SP_2C_2017_bis;

import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by nclozza on 03/12/17.
 */
public class GoldFinder {

  private static int moves[][] = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

  static boolean canFindGold(char[][] board, Point initial, int maxSteps) {
    if (initial.getX() < 0 || initial.getX() >= board.length
      || initial.getY() < 0 || initial.getY() >= board[0].length
      || maxSteps < 0) {
      throw new IllegalArgumentException();
    }

    Deque<QPoint> q = new LinkedList<>();
    q.offer(new QPoint(initial, maxSteps));
    QPoint aux;

    int x;
    int y;
    int maxX = board.length;
    int maxY = board[0].length;

    while (!q.isEmpty()) {
      aux = q.poll();
      x = (int) aux.position.getX();
      y = (int) aux.position.getY();

      if (aux.availableSteps > 0) {
        if (canMove(maxX, maxY, x + moves[0][0], y + moves[0][1])) {
          if (thereIsGold(board, x + moves[0][0], y + moves[0][1])) {
            return true;

          } else {
            if (!thereIsRock(board, x + moves[0][0], y + moves[0][1])) {
              q.offer(new QPoint(new Point(x + moves[0][0], y + moves[0][1]), aux.availableSteps - 1));
            }
          }
        }

        if (canMove(maxX, maxY, x + moves[1][0], y + moves[1][1])) {
          if (thereIsGold(board, x + moves[1][0], y + moves[1][1])) {
            return true;

          } else {
            if (!thereIsRock(board, x + moves[1][0], y + moves[1][1])) {
              q.offer(new QPoint(new Point(x + moves[1][0], y + moves[1][1]), aux.availableSteps - 1));
            }
          }
        }

        if (canMove(maxX, maxY, x + moves[2][0], y + moves[2][1])) {
          if (thereIsGold(board, x + moves[2][0], y + moves[2][1])) {
            return true;

          } else {
            if (!thereIsRock(board, x + moves[2][0], y + moves[2][1])) {
              q.offer(new QPoint(new Point(x + moves[2][0], y + moves[2][1]), aux.availableSteps - 1));
            }
          }
        }

        if (canMove(maxX, maxY, x + moves[3][0], y + moves[3][1])) {
          if (thereIsGold(board, x + moves[3][0], y + moves[3][1])) {
            return true;

          } else {
            if (!thereIsRock(board, x + moves[3][0], y + moves[3][1])) {
              q.offer(new QPoint(new Point(x + moves[3][0], y + moves[3][1]), aux.availableSteps - 1));
            }
          }
        }
      }
    }
    return false;
  }

  private static boolean canMove(int maxX, int maxY, int x, int y) {
    return x >= 0 && x < maxX && y >= 0 && y < maxY;
  }

  private static boolean thereIsRock(char[][] board, int x, int y) {
    return board[x][y] == 'R';
  }

  private static boolean thereIsGold(char[][] board, int x, int y) {
    return board[x][y] == 'G';
  }

  private static class QPoint {

    Point position;
    int availableSteps;

    QPoint(Point position, int availableSteps) {
      this.position = position;
      this.availableSteps = availableSteps;
    }
  }
}
