package models;

public class GameOfLifeCell {
    private boolean alive;

    public GameOfLifeCell(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public void die() {
        alive = false;
    }

    public void incarnate() {
        alive = true;
    }

    public void update(Iterable<GameOfLifeCell> surroundingCells) {
        //this cell is surrounded by how many living cells?
        int surroundingAliveCells = 0;
        for (GameOfLifeCell surroundingCell : surroundingCells) {
            if (surroundingCell.isAlive()) {
                surroundingAliveCells++;
            }
        }
        //depending on how many living cells surround this cell, something happens to this cell
        switch (surroundingAliveCells) {
            case 0:
            case 1:
                this.die();
                break;
            case 2:
                //nothing happens
                break;
            case 3:
                this.incarnate();
                break;
            default:
                this.die();
        }
    }
}
