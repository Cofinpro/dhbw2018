package koordinatensystem;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Scanner;

public class ResizeableCanvas extends Canvas{
    CoordinateSystem coordinateSystem;
    CoordinatePoint[] coordinatePoints;

    public ResizeableCanvas(CoordinateSystem cS) {
        widthProperty().addListener(e -> draw());
        heightProperty().addListener(e -> draw());
        coordinateSystem = cS;
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



    public void setPoints(){
        if (coordinatePoints != null && coordinatePoints.length > 0) {
            return;
        }
        double w = getWidth();
        double h = getHeight();

        GraphicsContext gc = getGraphicsContext2D();

        coordinatePoints = new CoordinatePoint[20];
        for (int pointCount=0; pointCount<coordinatePoints.length; pointCount++){
            coordinatePoints[pointCount] = new CoordinatePoint((int)(Math.random()*w*0.5)-h*0.5,(int)(Math.random()*w*0.5)-h*0.5);

        }
    }

    public void draw() {

        double w = getWidth();
        double h = getHeight();

        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.LIGHTGRAY);
        gc.clearRect(0,0,w,h);
        gc.fillRect(0,0,w,h);

        DrawCoordinateSystem drawCoordinateSystem = new DrawCoordinateSystem();
        drawCoordinateSystem.drawTemplate(w,h,gc,coordinateSystem);


        gc.setFill(Color.BLACK);

        if (coordinatePoints == null) {
            return;
        }
        for (int i = 0; i < coordinatePoints.length;i++){
            coordinatePoints[i].drawTemplate(w,h,gc,coordinateSystem);
        }



    }
}