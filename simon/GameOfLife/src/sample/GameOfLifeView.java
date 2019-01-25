package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseButton;
import models.GameOfLife;
import models.GameSituation;

public class GameOfLifeView extends Canvas {

    public GameOfLifeView() {
        widthProperty().addListener(e -> display());
        heightProperty().addListener(e -> display());
        setOnMousePressed(e -> {
            double x = e.getX();
            double y = e.getY();
            int row = getRow(y);
            int column = getColumn(x);
            if (row >= 0 && column >= 0) {
                GameOfLife.getInstance().switchCellAndDeletePreviousSituations(row, column);
            }
            display();
        });
        setOnMouseDragged(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                return; //left button not activated
            }
            int row = getRow(e.getY());
            int column = getColumn(e.getX());
            if (row >= 0 && column >= 0 && !GameOfLife.getInstance().isCellAliveAt(row, column)) {
                GameOfLife.getInstance().switchCellAndDeletePreviousSituations(row, column);
                display();
            }
        });
    }

    private int getRow(double y) {
        GameSituation gameSituation = GameOfLife.getInstance().getCurrentGameSituation();
        int rows = gameSituation.getRows();
        double rowHeight = getHeight()/rows;
        for (int row = 0; row < rows; row++) {
            if (y > rowHeight * row && y < rowHeight * (row+1)) {
                return row;
            }
        }
        return -1; //row not found
    }

    private int getColumn(double x) {
        GameSituation gameSituation = GameOfLife.getInstance().getCurrentGameSituation();
        int columns = gameSituation.getColumns();
        double columnWidth = getWidth()/columns;
        for (int column = 0; column < columns; column++) {
            if (x > columnWidth * column && x < columnWidth * (column + 1)) {
                return column;
            }
        }
        return -1; //column not found
    }

    public void display() {
        GameOfLife gameOfLife = GameOfLife.getInstance();
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
        for (int row = 0; row < gameSituation.getRows(); row++) {
            for (int column = 0; column < gameSituation.getColumns(); column++) {
                if (gameSituation.isCellAliveAt(row, column)) {
                    getGraphicsContext2D().fillRect(column * columnWidth, row * rowHeight, columnWidth, rowHeight);
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
