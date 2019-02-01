package models;

import helper.IntegerSortingEvaluater;
import interfaces.Sorter;
import javafx.scene.chart.XYChart;
import sorting.BubbleSort;
import sorting.PhilippSort;
import sorting.SimonSort;

import java.util.ArrayList;
import java.util.List;

public class Results {

    private static final int startArrayLength = 1;
    private static final int endArrayLength = 4001;
    private static final int increment = 100;
    private XYChart.Series<Number, Number>[] series;
    private Progress progress;

    private static Results ourInstance = new Results();

    public static Results getInstance() {
        return ourInstance;
    }

    private Results() {
        progress = new Progress();

    }

    public void calculateResults() {
        series = new XYChart.Series[3];
        series[0] = createResults(new SimonSort<>());
        progress.setRelativeProgress(1);
        series[1] = createResults(new PhilippSort<>());
        series[2] = createResults(new BubbleSort<>());
    }

    public XYChart.Series<Number, Number>[] getSeries() {
        return series;
    }

    public Progress getProgress() {
        return progress;
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
