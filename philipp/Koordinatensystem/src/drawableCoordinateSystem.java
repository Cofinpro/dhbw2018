import javafx.scene.paint.Color;

public class DrawableCoordinateSystem implements Shape {

    public DrawableCoordinateSystem(){

    }

    @Override
    public void drawShape(double w, double h, javafx.scene.canvas.GraphicsContext gc, CoordinateSystem coordinateSystem) {
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
    }
}
