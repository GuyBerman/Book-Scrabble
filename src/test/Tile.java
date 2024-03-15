package test;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
public class Tile {
    public final char letter;
    public final int score;
    private Tile(char letter , int score){
        this.letter = letter;
        this.score = score;
    }
//    public static Tile createTile(char letter , int score){
//        return new Tile(letter , score);
//    }

    public boolean equals(Object o){
        if(this==o) return true;
        else return false;
    }
    public int hashCode() {
        return Objects.hash(letter, score);
    }
    public static class Bag {
        private static Bag bag = null;
        private final int[] qua ={9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
        private final int[] max_qua ={9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
        private final Tile[] tiles = {
                new Tile('A' , 1),
                new Tile('B' , 3),
                new Tile('C' , 3),
                new Tile('D' , 2),
                new Tile('E' , 1),
                new Tile('F' , 4),
                new Tile('G' , 2),
                new Tile('H' , 4),
                new Tile('I' , 1),
                new Tile('J' , 8),
                new Tile('K' , 5),
                new Tile('L' , 1),
                new Tile('M' , 3),
                new Tile('N' , 1),
                new Tile('O' , 1),
                new Tile('P' , 3),
                new Tile('Q' , 10),
                new Tile('R' , 1),
                new Tile('S' , 1),
                new Tile('T' , 1),
                new Tile('U' , 1),
                new Tile('V' , 4),
                new Tile('W' , 4),
                new Tile('X' , 8),
                new Tile('Y' , 4),
                new Tile('Z' , 10),





        };

        private Bag() {
        }

        public Tile getRand() {
            int tile_index = new Random().nextInt(26);
            if(qua[tile_index] == 0){
                return null;
            }
            qua[tile_index]--;
            return tiles[tile_index];

        }

        public Tile getTile(char c) {
            for (int i = 0; i <26 ; i++) {
                if(tiles[i].letter == c){
                    if(qua[i]==0){
                        return null;
                    }
                    qua[i]--;
                    return tiles[i];
                }


            }
            return null;
        }

        public void put(Tile tile2) {
            if(tile2==null){
                return;
            }

            for (int i = 0; i <26 ; i++) {
                if(tiles[i].letter == tile2.letter) {
                    if(qua[i]+1<=max_qua[i]){
                        qua[i]++;
                        return;
                    }
                    return;
                }
            }
        }

        public int size() {
            int sum=0;
            for (int i = 0; i <26 ; i++) {
                 sum= sum+qua[i];
            }
            return sum;
        }

        public int[] getQuantities() {
            int [] new_qua = new int [qua.length];
            System.arraycopy(qua, 0, new_qua, 0, qua.length);
            return new_qua;
        }

        public static Bag getBag(){
            if(bag==null){
                bag = new Bag();
            }
            return bag;
        }
    }

}


