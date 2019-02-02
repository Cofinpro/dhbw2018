package views;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.Progress;
import models.Results;

public class Main extends Application {

    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("progView.fxml"));
        primaryStage.setTitle("Benchmarks");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
        root.setOnMouseClicked(event -> {
            Results.getInstance().calculateResults();
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}