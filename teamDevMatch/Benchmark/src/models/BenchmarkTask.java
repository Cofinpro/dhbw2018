package models;

import javafx.concurrent.Task;
import javafx.scene.chart.XYChart;

public class BenchmarkTask extends Task<XYChart.Series<Number, Number>[]> {
    @Override
    protected XYChart.Series<Number, Number>[] call() throws Exception {
        int max = 10;
        for (int i = 0; i < max; i++) {
            this.updateProgress(i, max);
            Thread.sleep(500);
        }

        return new XYChart.Series[0];
    }
}
