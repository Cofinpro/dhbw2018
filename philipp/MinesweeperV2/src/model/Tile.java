package model;

public class Tile {

    private boolean isFlagged;
    private int mineLevel; //0 for no mineLevel, 1 for normal mineLevel, 2 for super-mineLevel
    private int nearbyMines;
    private boolean isRevealed;

    public Tile() {
        this.isFlagged = false;
        this.mineLevel = 0;
        this.isRevealed = false;
        this.nearbyMines = 0;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        this.isFlagged = flagged;
    }

    public int getMineLevel() {
        return mineLevel;
    }

    public void setMineLevel(int mineLevel) {
        this.mineLevel = mineLevel;
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
        if (mineLevel > 0 && !isFlagged)
            return "X";
        if (isFlagged && !isRevealed)
            return "\u2665";
        if (nearbyMines == 0)
            return "";
        else
            return "" + nearbyMines;
    }
}
