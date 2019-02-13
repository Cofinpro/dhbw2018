package model;

public class Tile {


    private boolean isFlagged;
    private boolean isMine;
    private int nearbyMines;
    private boolean isRevealed;

    public Tile() {
        this.isFlagged = false;
        this.isMine = false;
        this.isRevealed = false;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setFlagged(boolean flagged) {
        this.isFlagged = flagged;
    }

    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed (boolean revealed) {
        this.isRevealed = revealed;
    }

    public int getNearbyMines() {
        return nearbyMines;
    }

    public void setNearbyMines(int count) {
        this.nearbyMines = count;
    }

    @Override
    public String toString() {
        if (isMine && !isFlagged)
            return "X";
        if (isFlagged)
            return "\u2665";
        if (nearbyMines == 0)
            return "";
        else
            return "" + nearbyMines;
    }
}
