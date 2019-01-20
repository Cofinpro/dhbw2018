package models;

import java.util.ArrayList;

public class GameSituation {

    private GameOfLifeCell[][] cells;

    public GameSituation() {
        cells = new GameOfLifeCell[15][15];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new GameOfLifeCell(false);
            }
        }
        cells[6][8].incarnate();
        cells[5][8].incarnate();
        cells[4][8].incarnate();
        cells[7][9].incarnate();
        cells[7][6].incarnate();
    }

    private GameSituation(GameOfLifeCell[][] cells) {
        this.cells = new GameOfLifeCell[cells.length][cells[0].length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                this.cells[i][j] = new GameOfLifeCell(cells[i][j].isAlive());
            }
        }
    }

    public GameSituation next() {
        GameSituation newSituation = new GameSituation(cells);
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                newSituation.cells[i][j].update(getSurroundingCells(i,j));
            }
        }
        return newSituation;
    }

    private Iterable<GameOfLifeCell> getSurroundingCells(int i, int j) {
        ArrayList<GameOfLifeCell> surroundingCells = new ArrayList<>();
        if ((i > 0) && (j > 0)) {
            surroundingCells.add(cells[i-1][j-1]);
        }
        if (j > 0) {
            surroundingCells.add(cells[i][j-1]);
        }
        if ((i < cells.length-1) && (j > 0)) {
            surroundingCells.add(cells[i+1][j-1]);
        }
        if ((i < cells.length-1)) {
            surroundingCells.add(cells[i+1][j]);
        }
        if ((i < cells.length-1) && (j < cells[i].length-1)) {
            surroundingCells.add(cells[i+1][j+1]);
        }
        if ((j < cells[i].length-1)) {
            surroundingCells.add(cells[i][j+1]);
        }
        if ((j < cells[i].length-1) && (i > 0)) {
            surroundingCells.add(cells[i-1][j+1]);
        }
        if ((i > 0)) {
            surroundingCells.add(cells[i-1][j]);
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

    private void switchCell(int row, int width) {
        cells[row][width].switchCell();
    }

    public class GameOfLifeCell {
        private boolean alive;

        private GameOfLifeCell(boolean alive) {
            this.alive = alive;
        }

        public boolean isAlive() {
            return alive;
        }

        private void die() {
            alive = false;
        }

        private void incarnate() {
            alive = true;
        }

        private void update(Iterable<GameOfLifeCell> surroundingCells) {
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
