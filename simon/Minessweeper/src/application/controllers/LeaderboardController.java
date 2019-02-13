package application.controllers;

import application.css.CSSMappings;
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

public class LeaderboardController {
    @FXML
    private TextField messageTextView;
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
            ResultController resultController = new ResultController(i, result);
            resultVBox.getChildren().add(resultController);
            i++;
        }
        if (LeaderboardManager.getInstance().isRecentResultNewRecord()) {
            messageTextView.setText("You have a new record! It should be marked in yellow!");
        } else {
            messageTextView.setText("You failed to overcome an old record! It should be marked in red!");
        }
    }

    public void onNextGameRequested(ActionEvent actionEvent) {
        SceneTraversalHelper.switchScene("main.fxml");
    }
}
