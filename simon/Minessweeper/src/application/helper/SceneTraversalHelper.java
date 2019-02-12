package application.helper;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneTraversalHelper {
    public static void switchScene(String name) {
        try {
            Stage primaryStage = Main.getPrimaryStage();
            Parent root = FXMLLoader.load(Main.class.getResource("views/" + name));
            primaryStage.setScene(new Scene(root));
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            primaryStage.setX(bounds.getMinX());
            primaryStage.setY(bounds.getMinY());
            primaryStage.setWidth(bounds.getWidth());
            primaryStage.setHeight(bounds.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
