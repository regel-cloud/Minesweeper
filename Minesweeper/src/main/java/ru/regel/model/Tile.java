package ru.regel.model;

public class Tile {

    int surroundingBombs;
    private boolean hasMine;
    private boolean hasFlag;
    private boolean isOpened;

    public int getSurroundingBombs() {
        return surroundingBombs;
    }

    public boolean hasMine() {
        return hasMine;
    }

    public void setMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public boolean hasFlag() {
        return hasFlag;
    }

    public void setFlag(boolean hasFlag) {
        this.hasFlag = hasFlag;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }
}
