package test;
import java.util.Arrays;
import java.util.Objects;


public class Word {
    private final Tile [] tiles;
    private final int row;
    private final int col;
    private final boolean vertical;
    public Word(Tile[]tile , int row , int col, boolean vertical){
        this.tiles = tile;
        this.row = row;
        this.col = col;
        this.vertical = vertical;
    }
    public int getCol() {
        return col;
    }
    public int getRow() {
        return row;
    }
    public Tile[] getTiles() {
        return tiles;
    }
    public boolean isVertical() {
        return vertical;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
