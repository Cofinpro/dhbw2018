package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage PRIMARY_STAGE;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("loginWindow.fxml"));
        primaryStage.setTitle("Lego City Bank");
        primaryStage.getIcons().addAll(new Image("images\\lego-logo.jpg"));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setScene(new Scene(root, screenBounds.getWidth(),screenBounds.getHeight()));
        primaryStage.show();
        PRIMARY_STAGE = primaryStage;
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        //TODO
        //saveEverything();
    }

    public static void main(String[] args) {
        launch(args);
    }
}