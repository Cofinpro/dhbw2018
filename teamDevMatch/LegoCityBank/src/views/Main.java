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
        Parent root = FXMLLoader.load(getClass().getResource("loginWindow.fxml"));
        primaryStage.setTitle("Login-Window");
        primaryStage.setScene(new Scene(root, 600,400));
        primaryStage.show();
        PRIMARY_STAGE = primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}