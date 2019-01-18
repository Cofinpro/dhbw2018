import javafx.scene.paint.Color;

public class CoordinatePoint implements Shape {
    double xValue;
    double yValue;

    public CoordinatePoint(double x, double y) {
        xValue = x;
        yValue = y;
    }

    @Override
    public void drawShape(double w, double h, javafx.scene.canvas.GraphicsContext gc, CoordinateSystem coordinateSystem) {
        double xTemp = ((coordinateSystem.maxX + xValue) / (2 * coordinateSystem.maxX)) * w - w*0.01; //1% offset because point is 2% wide and should be centered
        double yTemp = ((coordinateSystem.maxY - yValue) / (2 * coordinateSystem.maxY)) * h - h*0.01;

        //prints the points from the pointsArrayList
        gc.setFill(Color.RED);
        gc.fillOval(xTemp, yTemp, w * 0.02, h * 0.02);
        gc.strokeText("P (" + xValue + " | " + yValue + ")", xTemp + w / 100, yTemp - h / 100);
        //you could add a point counter for the following output sample: P1(5.67 | 5.67)
    }
}
