package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomMain extends Application {

    private String path;

    public CustomMain(String path) {
        this.path = path;
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        primaryStage.setTitle("Custom View");
        primaryStage.setScene(new Scene(root, 600,400));
        primaryStage.show();
    }
}
