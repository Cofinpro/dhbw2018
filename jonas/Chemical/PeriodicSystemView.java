package Chemical;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class PeriodicSystemView extends Application {

    public static void main(String[] args) {

        PeriodicSystem system = PeriodicSystem.getInstance();

        PeriodicSystem.getElementsInPeriodicSystem();
        ChemicalElement e = PeriodicSystem.getChemicalElement(4,4);


        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setTitle("PSE");

        StackPane stackPane = new StackPane();
        Parent root = FXMLLoader.load(getClass().getResource("SystemView.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
