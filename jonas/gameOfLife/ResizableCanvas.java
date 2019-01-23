package gameOfLife;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ResizableCanvas extends Canvas {
    GraphicsContext gc = getGraphicsContext2D();

    public ResizableCanvas() {
        widthProperty().addListener(e -> draw());
        heightProperty().addListener(e -> draw());
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double x = event.getX();
                double y = event.getY();
                mouse(x, y);
            }
        });
        setOnKeyPressed(event -> {


        });
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }

    public void draw() {
        double w = getWidth();
        double h = getHeight();

        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.clearRect(0, 0, w, h);
        gc.fillRect(0, 0, w, h);
        drawLines();
    }

    public void drawLines() {

        GameOfLifeCell[][] cells = GameOfLife.getInstance().getGameField();


        gc.setStroke(Color.BLACK);
        for (int i = 0; i < cells.length; i++) {
            gc.strokeLine(0, (getHeight() / 15) * (i + 1), getWidth(), (getHeight() / 15) * (i + 1));
        }
        for (int j = 0; j < cells.length; j++) {
            gc.strokeLine((getWidth() / 15) * (j + 1), 0, (getWidth() / 15) * (j + 1), getWidth());
        }
    }

    public void mouse(double x, double y) {
        GameOfLifeCell[][] cells = GameOfLife.getInstance().getGameField();

        int getRowHeight = (int) (getHeight() / 15);
        int getColumnWidth = (int) (getWidth() / 15);

        for (int i = 0; i < cells.length; i++) {
            if (i * getColumnWidth < x && (i + 1) * getColumnWidth > x) {
                for (int j = 0; j < GameOfLife.getInstance().getGameField().length; j++) {
                    if (j * getRowHeight < y && (j + 1) * getRowHeight > y) {
                        if (GameOfLife.getInstance().getGameField()[j][i].alive == true) {
                            GameOfLife.getInstance().getGameField()[j][i].alive = false;
                        } else {
                            GameOfLife.getInstance().getGameField()[j][i].alive = true;
                        }
                    }
                }
            }
        }
        colorChange();
        drawLines();
    }

    public void colorChange() {
        GameOfLifeCell[][] gf = GameOfLife.getInstance().getGameField();
        double columnWidth = getWidth() / 15;
        double rowHeight = getHeight() / 15;
        for (int i = 0; i < gf.length; i++) {
            for (int j = 0; j < gf[i].length; j++) {
                if (gf[i][j].alive) {
                    gc.setFill(Color.RED);
                    gc.fillRect(j * columnWidth, i * rowHeight, columnWidth, rowHeight);
                } else {
                    gc.setFill(Color.WHITE);
                    gc.fillRect(j * columnWidth, i * rowHeight, columnWidth, rowHeight);
                }
            }
        }
    }
}
