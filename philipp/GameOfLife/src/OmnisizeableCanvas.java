import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class OmnisizeableCanvas extends Canvas {

    public OmnisizeableCanvas() {
        widthProperty().addListener(e -> draw());
        heightProperty().addListener(e -> draw());
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

    }
}