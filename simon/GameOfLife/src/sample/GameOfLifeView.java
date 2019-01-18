package sample;

import javafx.scene.canvas.Canvas;
import models.GameOfLife;
import models.GameOfLifeCell;
import models.GameSituation;

public class GameOfLifeView extends Canvas {

    public GameOfLifeView() {
        widthProperty().addListener(e -> display());
        heightProperty().addListener(e -> display());
    }

    public void display() {
        GameOfLife gameOfLife = GameOfLife.getInstance();
        gameOfLife.setGameOfLifeView(this);
        GameSituation gameSituation = gameOfLife.getCurrentGameSituation();
        getGraphicsContext2D().clearRect(0,0, getWidth(), getHeight());
        display(gameSituation);
    }

    private void display(GameSituation gameSituation) {
        int rows = gameSituation.getRows();
        int columns = gameSituation.getColumns();
        double rowHeight = getHeight()/rows;
        double columnWidth = getWidth()/columns;
        drawGrid(gameSituation, rows, columns, rowHeight, columnWidth);
        drawCells(gameSituation, rowHeight, columnWidth);
    }

    private void drawCells(GameSituation gameSituation, double rowHeight, double columnWidth) {
        GameOfLifeCell[][] cells = gameSituation.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].isAlive()) {
                    getGraphicsContext2D().fillRect(j * columnWidth, i * rowHeight, columnWidth, rowHeight);
                }
            }
        }
    }

    private void drawGrid(GameSituation gameSituation, int rows, int columns, double rowHeight, double columnWidth) {
        for (int i = 1; i < rows; i++) {
            getGraphicsContext2D().strokeLine(0, rowHeight * i, getWidth(), rowHeight * i);
        }
        for (int i = 1; i < columns; i++) {
            getGraphicsContext2D().strokeLine(columnWidth*i, 0, columnWidth*i, getHeight());
        }
    }
}
