package application.models;

import application.interfaces.CSVModel;
import javafx.beans.binding.StringBinding;

import java.util.ArrayList;
import java.util.List;

public class Result implements CSVModel, Comparable<Result> {
    private String name;
    private Long timeSeconds;

    public Result(String name, Long timeSeconds) {
        this.name = name;
        this.timeSeconds = timeSeconds;
    }

    @Override
    public List<String> getRepresentation() {
        List<String> representation = new ArrayList<>();
        representation.add(name);
        representation.add(timeSeconds.toString());
        return representation;
    }

    @Override
    public int compareTo(Result o) {
        int comparision = timeSeconds.compareTo(o.timeSeconds);
        if (comparision == 0) {
            comparision = name.compareToIgnoreCase(o.name);
        }
        return comparision;
    }

    public String getName() {
        return name;
    }

    public Long getTimeSeconds() {
        return timeSeconds;
    }
}
