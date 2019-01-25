import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        psView();

    }

    public void psView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PSView.fxml"));
            AnchorPane pane = fxmlLoader.load();

            primaryStage.setMinWidth(400.0);
            primaryStage.setMinHeight(400.0);

            PSViewController psViewController = fxmlLoader.getController(); //Connection to Controller class
            psViewController.setMain(this);
            psViewController.initialize();

            Scene scene = new Scene(pane);

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
