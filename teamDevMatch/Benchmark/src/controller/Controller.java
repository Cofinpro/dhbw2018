package controller;

import helper.IntegerSortingEvaluater;
import interfaces.Sorter;
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
        lineChart.getData().add(createSimonsResults());
    }

    private XYChart.Series<Integer, Integer> createSimonsResults() {
        return createResults(new SimonSort<>());
    }

    private XYChart.Series<Integer, Integer> createResults(Sorter<Integer> sorter) {
        XYChart.Series<Integer, Integer> series = new XYChart.Series();
        series.setName(sorter.getClass().getSimpleName());
        IntegerSortingEvaluater sortingEvaluater = new IntegerSortingEvaluater();
        for (int i = startArrayLength; i < endArrayLength; i += increment) {
            long benchmark = sortingEvaluater.getTimeMillis(sorter, i);
            series.getData().add(new XYChart.Data(i, benchmark));
        }
        return series;
    }
}
