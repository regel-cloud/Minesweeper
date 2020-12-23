package ru.regel.model;

public class NumberTile extends OpenedTile {

    private final int surroundingBombs;

    protected NumberTile(int row, int column, int surroundingBombs) {
        super(row, column);
        this.surroundingBombs = surroundingBombs;
    }

    public int getSurroundingBombs() {
        return surroundingBombs;
    }
}
