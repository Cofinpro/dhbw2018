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

    public void setFlagged() {
        this.isFlagged = true;
    }

    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed (boolean isRevealed) {
        this.isRevealed = isRevealed;
    }

    public int getNearbyMines() {
        return nearbyMines;
    }

    public void setNearbyMines(int count) {
        this.nearbyMines = count;
    }

    @Override
    public String toString() {
        if (isMine)
            return "X";
        else
            return "" + nearbyMines;
    }
}
