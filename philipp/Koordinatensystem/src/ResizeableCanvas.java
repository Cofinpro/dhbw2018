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

        DrawableCoordinateSystem drawableCoordinateSystem = new DrawableCoordinateSystem();
        drawableCoordinateSystem.drawShape(w, h, gc, coordinateSystem);

        for (int pointCount=0; pointCount<coordinateSystem.pointsArrayList.size(); pointCount++){
            coordinateSystem.pointsArrayList.get(pointCount).drawShape(w, h, gc, coordinateSystem);
        }
    }
}