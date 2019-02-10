package application;

import application.enums.GameState;
import application.models.Game;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/main.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.show();

        setupWinningAction();
        setupLosingAction();
    }

    private void setupLosingAction() {
    }

    private void setupWinningAction() {
        Game.getInstance().getGameStateProperty().addListener(event -> Platform.runLater(() -> { //first update the ui, THEN do the following
            if (Game.getInstance().getGameState() == GameState.WON) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Game won");
                dialog.setHeaderText("Well done! You finished the game within " + Game.getInstance().getTimePlayed()/1000 + " seconds.");
                dialog.setContentText("Please enter your name:");

                Optional<String> result = dialog.showAndWait();
                result.ifPresent(s -> System.out.println("Your name: " + s));
            }
        }));

    }


    public static void main(String[] args) {
        launch(args);
    }
}
