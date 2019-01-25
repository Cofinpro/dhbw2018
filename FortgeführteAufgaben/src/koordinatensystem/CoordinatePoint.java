package koordinatensystem;

import javafx.scene.paint.Color;

public class CoordinatePoint implements Template{
    double xValue;
    double yValue;

    public CoordinatePoint(double x, double y) {
        xValue = x;
        yValue = y;
    }

    public double getxValue() {
        return xValue;
    }

    public double getyValue() {
        return yValue;
    }

    @Override
    public void drawTemplate(double w, double h, javafx.scene.canvas.GraphicsContext gc, CoordinateSystem coordinateSystem) {

        double markerSpaceX = w/10;
        double markerSpaceY = h/10;
        //prints the points from the pointsArrayList
        gc.setFill(Color.BLACK);

            gc.fillOval(0.5*w+xValue, 0.5*h-yValue,w*0.02, h*0.02);

    }

}