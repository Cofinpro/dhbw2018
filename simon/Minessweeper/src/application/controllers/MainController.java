package application.controllers;

import application.enums.GameState;
import application.models.Game;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import java.lang.management.PlatformLoggingMXBean;
import java.util.Timer;
import java.util.TimerTask;


public class MainController {
    @FXML
    private TextField textField;

    @FXML
    public void initialize() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long secondsPlayed = Game.getInstance().getTimePlayed()/1000;
                Platform.runLater(() -> textField.setText(String.valueOf(secondsPlayed)));
            }
        }, 1000, 1000);
    }
}
