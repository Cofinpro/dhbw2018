package application.controllers;

import application.enums.Difficulty;
import application.models.Game;
import application.models.Settings;
import javafx.application.Platform;
import javafx.beans.binding.IntegerBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.xml.soap.Text;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;


public class MainController {
    @FXML
    private TextField timerTextField;
    @FXML
    private TextField suspectedRemainingBombCountText;

    @FXML
    public void initialize() {
        Timer timer = new Timer();
        Settings settings = Settings.getInstance();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long secondsPlayed = Game.getInstance().getTimePlayed()/1000;
                Platform.runLater(() -> timerTextField.setText(secondsPlayed +  " ⏰"));
            }
        }, 1000, 1000);
        suspectedRemainingBombCountText.textProperty().bind(new IntegerBinding() {
            {
                bind(Game.getInstance().getSuspectedCellCountProperty());
            }
            @Override
            protected int computeValue() {
                Difficulty difficulty = Settings.getInstance().getDifficulty();
                if (difficulty == null) {
                    return 0;
                }
                int suspectedRemainingBombCount = difficulty.getBombCount() - Game.getInstance().getSuspectedCellCount();
                if (suspectedRemainingBombCount < 0) {
                    return 0;
                }
                return suspectedRemainingBombCount;
            }
        }.asString().concat(" \uD83D\uDCA3"));
    }

    public void onPlayRequested(ActionEvent actionEvent) {
        Game.getInstance().setup();
    }
}
