package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import model.HighScores;

import java.util.Optional;

public class UserNameIn {

    public static void readUserName(String title, String header, String content, String score) {
        TextInputDialog userInput = new TextInputDialog();
        userInput.setTitle(title);
        userInput.setHeaderText(header);
        userInput.setContentText(content);
        Optional<String> inputString = userInput.showAndWait();

        if (inputString.isPresent()) {
            String[] userWithHighScore = new String[2];
            userWithHighScore[0] = inputString.get();
            userWithHighScore[1] = score;
            HighScores.getInstance().addHighScore(userWithHighScore);
        }
        else {
            Alert noValueEntered = new Alert(Alert.AlertType.ERROR, "Please enter an username");
            noValueEntered.showAndWait();
            readUserName(title, header, content, score);
        }
    }
}
