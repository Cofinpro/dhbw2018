package sample;

import javafx.scene.canvas.Canvas;
import models.GameOfLife;
import models.GameOfLifeCell;
import models.GameSituation;

public class GameOfLifeView extends Canvas {

    public GameOfLifeView() {
        widthProperty().addListener(e -> display());
        heightProperty().addListener(e -> display());
        setOnMouseClicked(e -> {
            double x = e.getX();
            double y = e.getY();
            GameOfLifeCell cell = getCell(x, y);
            if (cell == null) {
                return;
            }
            if (cell.isAlive()) {
                cell.die();
            } else {
                cell.incarnate();
            }
            display();
        });
    }

    private GameOfLifeCell getCell(double x, double y) {
        GameSituation gameSituation = GameOfLife.getInstance().getCurrentGameSituation();
        int rows = gameSituation.getRows();
        int columns = gameSituation.getColumns();
        double rowHeight = getHeight()/rows;
        double columnWidth = getWidth()/columns;
        for (int i = 0; i < rows; i++) {
            if (y > rowHeight * i && y < rowHeight * (i+1)) {
                for (int j = 0; j < columns; j++) {
                    if (x > columnWidth * j && x < columnWidth * (j+1)) {
                        return GameOfLife.getInstance().getCurrentGameSituation().getCell(i, j);
                    }
                }
            }
        }
        return null;
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
