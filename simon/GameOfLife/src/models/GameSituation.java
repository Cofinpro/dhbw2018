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

    public GameSituation(GameOfLifeCell[][] cells) {
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

    public GameOfLifeCell getCell(int row, int width) {
        return cells[row][width];
    }
}
