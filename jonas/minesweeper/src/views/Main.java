package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage PRIMARY_STAGE;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Minesweeper");
        /*Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setScene(new Scene(root, screenBounds.getWidth(),screenBounds.getHeight()));*/
        primaryStage.setScene(new Scene(root));
        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.show();
        PRIMARY_STAGE = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}