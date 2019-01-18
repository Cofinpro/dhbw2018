package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class CoordinateSystemDisplayingCanvas extends Canvas {
    private DrawableCoordinateSystem drawableCoordinateSystem;

    public CoordinateSystemDisplayingCanvas(DrawableCoordinateSystem drawableCoordinateSystem) {
        this.drawableCoordinateSystem = drawableCoordinateSystem;
        widthProperty().addListener(evt -> draw());
        heightProperty().addListener(evt -> draw());
    }

    public void draw() {
        double width = getWidth();
        double height = getHeight();
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);
        drawableCoordinateSystem.drawEverything(gc);
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
}
