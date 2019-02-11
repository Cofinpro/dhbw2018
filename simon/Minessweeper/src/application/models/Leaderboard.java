package application.models;

import application.enums.Difficulty;
import application.interfaces.CSVModel;

import java.util.*;

public class Leaderboard implements CSVModel, Comparable<Leaderboard> {
    private Difficulty difficulty;
    private SortedSet<Result> results;

    public Leaderboard(Difficulty difficulty) {
        this.difficulty = difficulty;
        results = new TreeSet<>();
    }

    public boolean addResult(Result result) {
        return results.add(result);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return super.equals(obj);
        }
        return ((Leaderboard) obj).difficulty == this.difficulty;
    }

    @Override
    public List<String> getRepresentation() {
        List<String> representation = new ArrayList<>();
        representation.add(difficulty.toString());
        for (Result result : results) {
            representation.addAll(result.getRepresentation());
        }
        return representation;
    }

    @Override
    public int compareTo(Leaderboard o) {
        return difficulty.compareTo(o.difficulty);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
