package application.tasks;

import application.helper.IntegerSortingEvaluater;
import application.interfaces.Sorter;
import application.models.BenchmarkSettings;
import javafx.concurrent.Task;
import javafx.scene.chart.XYChart;

public class SeriesTask extends Task<XYChart.Series<Number, Number>> {

    private Sorter<Integer> sorter;

    public SeriesTask(Sorter<Integer> sorter) {
        super();
        this.sorter = sorter;
    }

    @Override
    protected XYChart.Series<Number, Number> call() throws Exception {
        XYChart.Series<Number, Number> series = new XYChart.Series();
        series.setName(sorter.getClass().getSimpleName());
        IntegerSortingEvaluater sortingEvaluater = new IntegerSortingEvaluater();
        BenchmarkSettings settings = BenchmarkSettings.getInstance();
        int startArrayLength = settings.getStartArrayLength();
        int endArrayLength = settings.getEndArrayLength();
        int increment = settings.getIncrement();
        for (int i = startArrayLength; i <= endArrayLength; i += increment) {
            long benchmark = sortingEvaluater.getTimeMillis(sorter, i, 100);
            series.getData().add(new XYChart.Data(i, benchmark));
            System.out.println(i);
            this.updateProgress(i, endArrayLength);
        }
        this.updateProgress(1,1);
        return series;
    }
}
