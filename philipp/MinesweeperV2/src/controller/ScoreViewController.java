package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import model.HighScores;

import java.util.ArrayList;

public class ScoreViewController {

    //tut: https://docs.oracle.com/javafx/2/fxml_get_started/custom_control.htm

    @FXML
    private VBox scoresVbox;

    @FXML
    public void initialize() {
        ArrayList<String[]> usersWithHighScores = HighScores.getInstance().getUsersWithHighscoresAtCurrentDifficulty();
        for (String[] userWithHighScore : usersWithHighScores) {
            SingleScoreController sc = new SingleScoreController();
            sc.setTextFields(userWithHighScore[1], userWithHighScore[2]);
            scoresVbox.getChildren().add(sc);
        }
    }
}
