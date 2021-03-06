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

    public Result addResult(Result result) {
        removeWorseResultsOfSamePerson(result);
        Optional<Result> betterOrEqualResult = getBetterOrEqualResultBySamePerson(result);
        if (!betterOrEqualResult.isPresent()) {
            results.add(result);
            return result;
        }
        return betterOrEqualResult.get();
    }

    private Optional<Result> getBetterOrEqualResultBySamePerson(Result reference) {
        return  results.stream().filter(r -> r.getName().equals(reference.getName()) && r.getTimeSeconds() <= reference.getTimeSeconds()).findFirst();
    }

    private void removeWorseResultsOfSamePerson(Result result) {
        Set<Result> worseResults = new TreeSet<>();
        results.stream().filter(r -> r.getName().equals(result.getName()) && r.getTimeSeconds() > result.getTimeSeconds()).forEach(worseResults::add);
        results.removeAll(worseResults);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return super.equals(obj);
        }
        return this.compareTo((Leaderboard) obj) == 0;
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

    Difficulty getDifficulty() {
        return difficulty;
    }

    public SortedSet<Result> getResults() {
        return results;
    }
}
