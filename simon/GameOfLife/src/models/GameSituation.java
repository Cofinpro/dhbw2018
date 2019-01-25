package models;

import java.util.ArrayList;

public class GameSituation {

    private GameOfLifeCell[][] cells;

    public GameSituation(int rows, int columns) {
        cells = new GameOfLifeCell[rows][columns];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new GameOfLifeCell(false);
            }
        }
    }

    /**
     * creates a whole copy
     * @param cells the cells that will be copied
     */
    private GameSituation(GameOfLifeCell[][] cells) {
        this.cells = new GameOfLifeCell[cells.length][cells[0].length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                this.cells[i][j] = new GameOfLifeCell(cells[i][j].isAlive());
            }
        }
    }

    /**
     * creates a whole copy
     * @param gameSituationToCopy the copied Game Situation
     */
    private GameSituation(GameSituation gameSituationToCopy) {
        GameOfLifeCell[][] cells = gameSituationToCopy.getCells();
        this.cells = new GameOfLifeCell[cells.length][cells[0].length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                this.cells[i][j] = new GameOfLifeCell(cells[i][j].isAlive());
            }
        }
    }

    public GameSituation getNextGameSituation() {
        GameSituation newSituation = new GameSituation(cells);
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                newSituation.cells[i][j].liveOrDie(getSurroundingCells(i,j));
            }
        }
        return newSituation;
    }

    /**
     * Gets all cells that surround a cell with a specific row and column
     * @param row row of the surrounded cell
     * @param column column of the surrounded cell
     * @return all cells surrounding the specific cell, no matter alive or dead
     */
    private Iterable<GameOfLifeCell> getSurroundingCells(int row, int column) {
        ArrayList<GameOfLifeCell> surroundingCells = new ArrayList<>();
        if ((row > 0) && (column > 0)) {
            surroundingCells.add(cells[row-1][column-1]);
        }
        if (column > 0) {
            surroundingCells.add(cells[row][column-1]);
        }
        if ((row < cells.length-1) && (column > 0)) {
            surroundingCells.add(cells[row+1][column-1]);
        }
        if ((row < cells.length-1)) {
            surroundingCells.add(cells[row+1][column]);
        }
        if ((row < cells.length-1) && (column < cells[row].length-1)) {
            surroundingCells.add(cells[row+1][column+1]);
        }
        if ((column < cells[row].length-1)) {
            surroundingCells.add(cells[row][column+1]);
        }
        if ((column < cells[row].length-1) && (row > 0)) {
            surroundingCells.add(cells[row-1][column+1]);
        }
        if ((row > 0)) {
            surroundingCells.add(cells[row-1][column]);
        }
        return  surroundingCells;
    }

    public int getRows() {
        return cells.length;
    }

    public int getColumns() {
        return cells[0].length;
    }

    public GameOfLifeCell[][] getCells() {
        return cells;
    }

    public void switchCell(int row, int column) {
        cells[row][column].switchCell();
    }

    public boolean isCellAliveAt(int row, int column) {
        return cells[row][column].isAlive();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");
        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                if (isCellAliveAt(row, column)) {
                    result.append("x");
                } else {
                    result.append(" ");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return super.equals(obj);
        }
        GameSituation other = (GameSituation) obj;
        if (getRows() != other.getRows() || getColumns() != other.getColumns()) {
            return false;
        }
        for (int row  = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                if (isCellAliveAt(row, column) != other.isCellAliveAt(row, column)) {
                    return false;
                }
            }
        }
        return true;
    }

    private class GameOfLifeCell {
        private boolean alive;

        private GameOfLifeCell(boolean alive) {
            this.alive = alive;
        }

        private boolean isAlive() {
            return alive;
        }

        private void die() {
            alive = false;
        }

        private void incarnate() {
            alive = true;
        }

        private void liveOrDie(Iterable<GameOfLifeCell> surroundingCells) {
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

        private void switchCell() {
            if (isAlive()) {
                die();
            } else {
                incarnate();
            }
        }
    }
}
