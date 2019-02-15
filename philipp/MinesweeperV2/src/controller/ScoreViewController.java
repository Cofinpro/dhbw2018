package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.HighScores;

import java.util.ArrayList;

public class ScoreViewController {

    @FXML
    private VBox scoresVbox;

    @FXML
    private TextField scoresHeader;

    @FXML
    public void initialize() {
        ArrayList<String[]> usersWithHighScores = HighScores.getInstance().getUsersWithHighscores();
        scoresHeader.setText("HighScores");
        for (String[] userWithHighScore : usersWithHighScores) {
            SingleScoreController singleScoreController = new SingleScoreController(userWithHighScore[0], userWithHighScore[1]);
            scoresVbox.getChildren().add(singleScoreController.getScoreHbox());
        }
    }
}
