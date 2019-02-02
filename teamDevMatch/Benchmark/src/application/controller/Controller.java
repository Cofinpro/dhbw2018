package application.controller;

import application.helper.IntegerSortingEvaluater;
import application.interfaces.Sorter;
import application.tasks.SeriesTask;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import application.sorting.BubbleSort;
import application.sorting.MergeSort;
import application.sorting.QuickSort;
import javafx.scene.control.ProgressBar;

import java.util.List;
import java.util.Stack;

public class Controller {

    public ProgressBar progressBar;
    @FXML
    private LineChart<Number, Number> lineChart;
    private Stack<SeriesTask> tasks;

    @FXML
    public void initialize() {
        XYChart.Series<Number, Number> series = new XYChart.Series();
        tasks = new Stack<>();
        tasks.add(createSeriesTask(new QuickSort<>()));
        tasks.add(createSeriesTask(new BubbleSort<>()));
        tasks.add(createSeriesTask(new MergeSort<>()));
        startNextTaskIfPossible();
    }

    private SeriesTask createSeriesTask(Sorter<Integer> sorter) {
        SeriesTask currentTask = new SeriesTask(sorter);
        currentTask.setOnSucceeded(e -> {
            lineChart.getData().add(currentTask.getValue());
            startNextTaskIfPossible();
        });
        return currentTask;
    }

    private void startNextTaskIfPossible() {
        if (!tasks.isEmpty()) {
            Task task = tasks.pop();
            progressBar.progressProperty().unbind();
            progressBar.progressProperty().bind(task.progressProperty());
            new Thread(task).start();
        }
    }

}
