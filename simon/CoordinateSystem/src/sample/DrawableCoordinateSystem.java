package sample;

import com.sun.javafx.property.adapter.PropertyDescriptor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.Observable;

public class DrawableCoordinateSystem extends CoordinateSystem {
    private double pointHeight = 5;
    private double arrowLength = 5;

    public DrawableCoordinateSystem(double maxX, double maxY, double minDistanceOfPoints) {
        super(maxX, maxY, minDistanceOfPoints);
    }

    public void drawEverything(GraphicsContext gc) {
        drawAxes(gc);
        drawPoints(gc);
        drawFunctions(gc);
    }

    private void drawFunctions(GraphicsContext gc) {
        for (Function f : functions) {
            drawFunction(gc, f);
        }
    }

    private double getRatioX(GraphicsContext gc) {
        return gc.getCanvas().getWidth() / (maxX*2);
    }

    private double getRatioY(GraphicsContext gc) {
        return gc.getCanvas().getHeight() / (maxY*2);
    }

    private double getDisplayX(GraphicsContext gc, double realX){
        return (maxX+realX) * getRatioX(gc);
    }

    private double getDisplayY(GraphicsContext gc, double realY) {
        return (maxY - realY) * getRatioY(gc);
    }

    private void drawFunction(GraphicsContext gc, Function f) {
        double ratioX = getRatioX(gc);
        double ratioY = getRatioY(gc);
        double incrementStep = 0.1;
        double x0 = -maxX;
        double y0 = f.getY(x0);
        do {
            double x1 = x0 + incrementStep;
            double y1 = f.getY(x1);
            gc.strokeLine(getDisplayX(gc, x0), getDisplayY(gc, y0), getDisplayX(gc, x1), getDisplayY(gc, y1));
            x0 = x1;
            y0 = f.getY(x0);
        } while (x0 <= maxX);
    }

    private void drawPoints(GraphicsContext gc) {
        for (Point point : this.getPoints()) {
            drawPoint(gc, point);
        }
    }

    @Override
    void add(Point point) {
        super.add(point);
    }

    public void drawPoint(GraphicsContext gc, Point point) {
        //the next two lines of code stroke a cross in order to display a point
        gc.strokeLine(getDisplayX(gc, point.getX())-pointHeight/2, getDisplayY(gc, point.getY())-pointHeight/2, getDisplayX(gc, point.getX())+pointHeight/2, getDisplayY(gc, point.getY())+pointHeight/2);
        gc.strokeLine(getDisplayX(gc, point.getX())-pointHeight/2, getDisplayY(gc, point.getY())+pointHeight/2, getDisplayX(gc, point.getX())+pointHeight/2, getDisplayY(gc, point.getY())-pointHeight/2);
        gc.setStroke(javafx.scene.paint.Color.DARKBLUE);
        gc.strokeText(point.toString(), getDisplayX(gc, point.getX()), getDisplayY(gc, point.getY()) + pointHeight*3);
        gc.setStroke(Color.BLACK);
    }

    public void drawAxes(GraphicsContext gc) {
        Canvas canvas = gc.getCanvas();
        //horizontal line
        gc.strokeLine( canvas.getWidth()/2, canvas.getHeight(), canvas.getWidth()/2, 0);
        //arrows
        gc.strokeLine(canvas.getWidth()/2, 0, canvas.getWidth()/2 - arrowLength, arrowLength);
        gc.strokeLine(canvas.getWidth()/2, 0, canvas.getWidth()/2 + arrowLength, arrowLength);
        //vertical line
        gc.strokeLine(0, canvas.getHeight()/2, canvas.getWidth(), canvas.getHeight()/2);
        //arrows
        gc.strokeLine(canvas.getWidth(), canvas.getHeight()/2, canvas.getWidth() - arrowLength, canvas.getHeight()/2 - arrowLength);
        gc.strokeLine(canvas.getWidth(), canvas.getHeight()/2, canvas.getWidth() - arrowLength, canvas.getHeight()/2 + arrowLength);
    }
}
