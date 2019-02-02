package controller;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import models.BenchmarkTask;
import models.Results;
import views.Main;

import java.io.IOException;

public class progressViewController {

    private BenchmarkTask benchmarkTask;

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
    public void initialize() throws IOException, InterruptedException {
        text1.setText("Current Iteration: ");
        text2.setText("Current Sorting Method: ");
        text3.setText("Total Progress: ");
        progBar1.setProgress(0);
        benchmarkTask = new BenchmarkTask();
        progBar1.progressProperty().unbind();
        progBar1.progressProperty().bind(benchmarkTask.progressProperty());
        benchmarkTask.addEventFilter(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("sample.fxml"));
                Main.getPrimaryStage().setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        new Thread(benchmarkTask).start();
    }
}
