package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import models.Progress;
import models.Results;
import views.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProgBar {

    @FXML
    private TextField text1;

    @FXML
    private ProgressBar progBar1;

    @FXML
    private TextField text2;

    @FXML
    private ProgressBar progBar2;

    @FXML
    private TextField text3;

    @FXML
    private ProgressBar progBar3;

    @FXML
    public void initialize() throws IOException {
        text1.setText("Current Iteration: ");
        text2.setText("Current Sorting Method: ");
        text3.setText("Total Progress: ");
        /*Results results = Results.getInstance();

        results.getProgress().addObserver((a, b) -> {
            progBar1.setProgress(results.getProgress().getRelativeProgress());
        });*/
        Platform.runLater(() -> {
            Results.getInstance().calculateResults();
            Parent root = null;
            try {
                root = FXMLLoader.load(Main.class.getResource("sample.fxml"));
                Main.getPrimaryStage().setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }
}
