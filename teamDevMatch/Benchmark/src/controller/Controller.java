package controller;

import helper.IntegerSortingEvaluater;
import interfaces.Sorter;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import sorting.BubbleSort;
import sorting.PhilippSort;
import sorting.SimonSort;

public class Controller {

    private static final int startArrayLength = 1;
    private static final int endArrayLength = 4001;
    private static final int increment = 100;

    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    public void initialize() {
        XYChart.Series<Number, Number> series = new XYChart.Series();

        lineChart.getData().add(createResults(new SimonSort<>()));
        lineChart.getData().add(createResults(new PhilippSort<>()));
        lineChart.getData().add(createResults(new BubbleSort<>()));

    }

    private XYChart.Series<Number, Number> createResults(Sorter<Integer> sort) {
        XYChart.Series<Number, Number> series = new XYChart.Series();
        series.setName(sort.getClass().getSimpleName());
        System.out.println(series.getName());
        IntegerSortingEvaluater sortingEvaluater = new IntegerSortingEvaluater();
        for (int i = startArrayLength; i < endArrayLength; i += increment) {
            long benchmark = sortingEvaluater.getTimeMillis(sort, i, 50);
            series.getData().add(new XYChart.Data(i, benchmark));
            System.out.println(i);
        }
        return series;
    }

}
