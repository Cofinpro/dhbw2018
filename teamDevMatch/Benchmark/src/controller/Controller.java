package controller;

import helper.IntegerSortingEvaluater;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import sorting.SimonSort;

public class Controller {

    private static final int startArrayLength = 1;
    private static final int endArrayLength = 10000;
    private static final int increment = 1000;

    @FXML
    private LineChart<Integer, Integer> lineChart;

    @FXML
    public void initialize() {

        XYChart.Series<Integer, Integer> series = new XYChart.Series();
        addSimonsResults(series);
        series.setName("SimonSort");
        lineChart.getData().add(series);
    }

    private void addSimonsResults(XYChart.Series<Integer, Integer> series) {
        IntegerSortingEvaluater sortingEvaluater = new IntegerSortingEvaluater();
        for (int i = startArrayLength; i < endArrayLength; i += increment) {
            long benchmark = sortingEvaluater.getTimeMillis(new SimonSort<Integer>(), i);
            series.getData().add(new XYChart.Data(i, benchmark));
        }
    }

}
