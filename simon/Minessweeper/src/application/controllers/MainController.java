package application.controllers;

import application.enums.Difficulty;
import application.models.Game;
import application.models.Settings;
import javafx.application.Platform;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;


public class MainController {
    @FXML
    private DifficultyPickerController difficultyPickerControl;
    @FXML
    private TextField difficultyDescription;
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
        difficultyDescription.textProperty().bind(new StringBinding() {
            {
                bind(Settings.getInstance().getDifficultyProperty());
            }
            @Override
            protected String computeValue() {
                Difficulty difficulty = Settings.getInstance().getDifficulty();
                if (difficulty == null) {
                    return "";
                }
                return difficulty.getDescription();
            }
        });
        Game.getInstance().setup();
        suspectedRemainingBombCountText.textProperty().bind(Game.getInstance().getSuspectedRemainingBombCountBinding().asString().concat(" \uD83D\uDCA3"));
    }

    public void onPlayRequested(ActionEvent actionEvent) {
        if (Settings.getInstance().getDifficulty() == null) {
            difficultyPickerControl.chooseDefault();
        }
        Game.getInstance().setup();
    }
}
