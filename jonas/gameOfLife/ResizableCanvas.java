package gameOfLife;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ResizableCanvas extends Canvas {
    public static final int MAX_GOL_HEIGHT = 15;
    GraphicsContext gc = getGraphicsContext2D();

    // resizes the canvas depending on current scene width and recognizes handleCellOnMouseClick clicks
    public ResizableCanvas() {
        widthProperty().addListener(e -> draw());
        heightProperty().addListener(e -> draw());
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double x = event.getX();
                double y = event.getY();
                handleCellOnMouseClick(x, y);
            }
        });
    }


    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }

    // clears the complete canvas
    public void draw() {
        double w = getWidth();
        double h = getHeight();

        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.clearRect(0, 0, w, h);
        gc.fillRect(0, 0, w, h);
        drawLines();
    }

    // draws the lines to create rows and columns
    public void drawLines() {

        GameOfLifeCell[][] cells = GameOfLife.getInstance().getGameField();


        gc.setStroke(Color.BLACK);
        for (int i = 0; i < cells.length; i++) {
            gc.strokeLine(0, (getHeight() / MAX_GOL_HEIGHT) * (i + 1), getWidth(), (getHeight() / 15) * (i + 1));
        }
        for (int j = 0; j < cells.length; j++) {
            gc.strokeLine((getWidth() / 15) * (j + 1), 0, (getWidth() / 15) * (j + 1), getWidth());
        }
    }

    //depending on the x and y gets the field the user clicked to and changes its state
    public void handleCellOnMouseClick(double x, double y) {
        GameOfLifeCell[][] cells = GameOfLife.getInstance().getGameField();

        int getRowHeight = (int) (getHeight() / 15);
        int getColumnWidth = (int) (getWidth() / 15);

        for (int i = 0; i < cells.length; i++) {
            if (i * getColumnWidth < x && (i + 1) * getColumnWidth > x) {
                for (int j = 0; j < GameOfLife.getInstance().getGameField().length; j++) {
                    if (j * getRowHeight < y && (j + 1) * getRowHeight > y) {
                        if (GameOfLife.getInstance().getGameField()[j][i].isAlive()) {
                            GameOfLife.getInstance().getGameField()[j][i].setAlive(false);
                        } else {
                            GameOfLife.getInstance().getGameField()[j][i].setAlive(true);
                        }
                    }
                }
            }
        }
        colorChange();
        drawLines();
    }

    // changes the color of clicked rectangle depending on original state
    public void colorChange() {
        GameOfLifeCell[][] gf = GameOfLife.getInstance().getGameField();
        double columnWidth = getWidth() / 15;
        double rowHeight = getHeight() / 15;
        for (int row = 0; row < gf.length; row++) {
            for (int column = 0; column < gf[row].length; column++) {
                if (gf[row][column].isAlive()) {
                    gc.setFill(Color.RED);
                    gc.fillRect(column * columnWidth, row * rowHeight, columnWidth, rowHeight);
                } else {
                    gc.setFill(Color.WHITE);
                    gc.fillRect(column * columnWidth, row * rowHeight, columnWidth, rowHeight);
                }
            }
        }
    }
}
