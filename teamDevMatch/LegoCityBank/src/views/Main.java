package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage PRIMARY_STAGE;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("loginWindow.fxml"));
        primaryStage.setTitle("Lego City Bank");
        primaryStage.getIcons().addAll(new Image("images\\lego-logo.jpg"));
        primaryStage.setScene(new Scene(root, 900,600));
        primaryStage.show();
        PRIMARY_STAGE = primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}