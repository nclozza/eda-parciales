package SR_1C_2016;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Ejercicio3 {

    public static enum Direction{DOWN, RIGHT};

    public static List<Direction> maxCoins(boolean[][] board) {
        Pack pack = new Pack();
        pack = maxCoinsR(board, 0, 0,  pack);
        System.out.println(pack.coins);
        return pack.list;
    }

    private static Pack maxCoinsR(boolean[][] board, int i, int j, Pack pack) {
        if (i == board.length-1 && j == board[0].length-1){
            if(board[i][j]) {
                pack.coins++;
            }
            return pack;
        }

        if(board[i][j]) {
            pack.coins++;
        }

        Pack downPack = new Pack();
        Pack rightPack = new Pack();

        downPack.coins = rightPack.coins = pack.coins;

        for (Direction d : pack.list) {
            downPack.list.add(d);rightPack.list.add(d);
        }

        if (i != board.length-1){
            downPack.list.add(Direction.DOWN);
            downPack= maxCoinsR(board, i+1, j, downPack);
        }
        if (j != board[0].length-1){
            rightPack.list.add(Direction.RIGHT);
            rightPack = maxCoinsR(board, i, j+1, rightPack);
        }

        return downPack.coins >= rightPack.coins ? downPack : rightPack;
    }


    public static void main(String[] args){
        boolean[][] tab = {{true,false},{false,true}};
        List<Direction> l = maxCoins(tab);
        for(Direction d : l){
            System.out.print(d + " ");
        }
    }

    private static class Pack {
        List<Direction> list;
        int coins = 0;

        public Pack() {
            this.list = new LinkedList<>();
        }

        public List<Direction> getList() {
            return list;
        }

        public void setList(List<Direction> list) {
            this.list = list;
        }

        public int getCoins() {
            return coins;
        }

        public void setCoins(int coins) {
            this.coins = coins;
        }
    }
}
