package application;

import application.enums.GameState;
import application.helper.SceneTraversalHelper;
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

import java.io.IOException;
import java.util.Optional;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage;
        primaryStage.setTitle("Hello World");
        SceneTraversalHelper.switchScene("main.fxml");
        primaryStage.show();
        setupWinningAction();
        setupLosingAction();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        LeaderboardDao.save();
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
                    Result result = new Result(s, secondsPlayed);
                    LeaderboardManager.getInstance().handleNewResult(result);
                    SceneTraversalHelper.switchScene("leaderboard.fxml");
                });
            }
        }));
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
