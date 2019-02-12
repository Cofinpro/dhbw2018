package application.controllers;

import application.Main;
import application.enums.Difficulty;
import application.helper.SceneTraversalHelper;
import application.models.Leaderboard;
import application.models.LeaderboardManager;
import application.models.Result;
import application.models.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Set;

public class LeaderboardController {
    @FXML
    private VBox resultVBox;
    @FXML
    private TextField difficultyTextView;

    @FXML
    public void initialize() {
        Difficulty difficulty = Settings.getInstance().getDifficulty();
        difficultyTextView.setText(difficulty.toString());
        Leaderboard leaderboard = LeaderboardManager.getInstance().getLeaderboard(difficulty);
        if (leaderboard == null) {
            return;
        }
        int i = 1;
        for (Result result : leaderboard.getResults()) {
            resultVBox.getChildren().add(new ResultController(i, result));
            i++;
        }
    }

    public void onNextGameRequested(ActionEvent actionEvent) {
        SceneTraversalHelper.switchScene("main.fxml");
    }
}
