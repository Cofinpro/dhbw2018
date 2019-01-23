import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class TestVisualOutput extends Application {
    public void start(Stage stage){
        stage.setTitle("testTitel");
        stage.setMinHeight(200);
        stage.setMinWidth(300);
        stage.setResizable(true);
        stage.sizeToScene();

        Pane pane = new Pane();

        Line line = new Line(10, 10, 100, 100);
        line.setStroke(Color.BLUE);

        Group root = new Group();
        root.getChildren().add(line);

        pane.getChildren().add(root);
        Scene scene = new Scene(pane);



        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
