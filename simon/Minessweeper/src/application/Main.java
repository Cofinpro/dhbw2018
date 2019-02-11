package application;

import application.enums.GameState;
import application.models.Game;
import application.models.LeaderboardManager;
import application.models.Result;
import application.persistance.LeaderboardDao;
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

    @Override
    public void stop() throws Exception {
        LeaderboardDao.save();
        super.stop();
    }

    private void setupLosingAction() {
    }

    private void setupWinningAction() {
        Game.getInstance().getGameStateProperty().addListener(event -> Platform.runLater(() -> { //first update the ui, THEN do the following
            if (Game.getInstance().getGameState() == GameState.WON) {
                long secondsPlayed = Game.getInstance().getTimePlayed()/1000;
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Game won");
                dialog.setHeaderText("Well done! You finished the game within " + secondsPlayed + " seconds.");
                dialog.setContentText("Please enter your name:");
                Optional<String> input = dialog.showAndWait();
                input.ifPresent(s -> {
                    System.out.println("Your name: " + s);
                    Result result = new Result(s, secondsPlayed);
                    LeaderboardManager.getInstance().handleNewResult(result);
                });
            }
        }));

    }

    public static void main(String[] args) {
        launch(args);
    }
}
