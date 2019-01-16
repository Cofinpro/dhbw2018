import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ResizeableCanvas extends Canvas {
    CoordinateSystem coordinateSystem;

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

    public void draw() {
        double w = getWidth();
        double h = getHeight();

        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.LIGHTGRAY);
        gc.clearRect(0,0,w,h);
        gc.fillRect(0,0,w,h);

        //prints the x and y axis
        gc.setStroke(Color.BLACK);
        gc.strokeLine(0,h/2,w,h/2);
        gc.strokeLine(w/2,0,w/2,w);

        //calculates the necessary distance between two markers
        double markerSpaceX = w/10;
        double markerSpaceY = h/10;

        //prints the markers
        for(double countX = 0; countX<w; countX += markerSpaceX) { //prints left markers first
            gc.strokeLine(countX, h/2 + markerSpaceY / 10, countX, h/2 - markerSpaceY / 10);
        }

        for(double countY = h; countY>0; countY -= markerSpaceY) { //prints lower markers first
            gc.strokeLine(w/2 + markerSpaceX / 10, countY, w/2 - markerSpaceX / 10, countY);
        }

        //prints the axis labels with ~5% offset
        gc.strokeText(coordinateSystem.labelX,w*0.95,h*0.55);
        gc.strokeText(coordinateSystem.labelY, w*0.55,h*0.05);

        //prints the points from the pointsArrayList
        gc.setFill(Color.RED);
        for (int pointCount=0; pointCount<coordinateSystem.pointsArrayList.size(); pointCount++){
            double xTemp = (coordinateSystem.maxX + coordinateSystem.pointsArrayList.get(pointCount).xValue)/(2*coordinateSystem.maxX)*w;
            double yTemp = (coordinateSystem.maxY - coordinateSystem.pointsArrayList.get(pointCount).yValue)/(2*coordinateSystem.maxY)*h;
            gc.fillOval(xTemp, yTemp,w*0.02, h*0.02);
            gc.strokeText("P" + (pointCount+1) +": (" + coordinateSystem.pointsArrayList.get(pointCount).xValue + " | "+
                            coordinateSystem.pointsArrayList.get(pointCount).yValue + ")",
                    xTemp + markerSpaceX/10,yTemp - markerSpaceY/10);
        }
    }
}