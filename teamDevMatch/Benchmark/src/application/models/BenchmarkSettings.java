package application.models;

public class BenchmarkSettings {
    private static BenchmarkSettings ourInstance = new BenchmarkSettings();

    public static BenchmarkSettings getInstance() {
        return ourInstance;
    }

    private int startArrayLength = 0;
    private int endArrayLength = 4000;
    private int increment = 400;

    private BenchmarkSettings() {
    }

    public int getStartArrayLength() {
        return startArrayLength;
    }

    public int getEndArrayLength() {
        return endArrayLength;
    }

    public int getIncrement() {
        return increment;
    }
}
