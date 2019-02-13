package application.persistance;

import application.enums.Difficulty;
import application.helper.CSVHelper;
import application.models.Leaderboard;
import application.models.LeaderboardManager;
import application.models.Result;

import java.util.*;

public class LeaderboardDao {

    private static final String LEADERBOARD_CSV_PATH = "resources\\users.csv";

    public static void save() {
        save(LEADERBOARD_CSV_PATH, LeaderboardManager.getInstance().getLeaderboards());
    }

    public static SortedSet<Leaderboard> getLeaderboards() {
        return getLeaderboards(LEADERBOARD_CSV_PATH);
    }

    static void save(String path, SortedSet<Leaderboard> leaderboards) {
        String[] leaderboardPresentations = new String[leaderboards.size()];
        Iterator<Leaderboard> leaderboardIterator = leaderboards.iterator();
        for (int i = 0; i < leaderboardPresentations.length; i++) {
            StringBuilder representation =  new StringBuilder();
            for (String singleValue : leaderboardIterator.next().getRepresentation()) {
                representation.append(singleValue);
                representation.append(',');
            }
            if (representation.length() > 0) {
                representation.deleteCharAt(representation.length()-1);
            }
            leaderboardPresentations[i] = representation.toString();
        }
        CSVHelper.writeCSV(path, leaderboardPresentations);
    }

    static SortedSet<Leaderboard> getLeaderboards(String path) {
        SortedSet<Leaderboard> results = new TreeSet<>();
        Collection<String[]> leaderboardRepresentations = CSVHelper.readCSV(path);
        for (String[] leaderboardRepresentation : leaderboardRepresentations) {
            Difficulty difficulty = Difficulty.getDifficultyByRepresentation(leaderboardRepresentation[0]);
            Leaderboard leaderboard = new Leaderboard(difficulty);
            for (int i = 1; i < leaderboardRepresentation.length; i += 2) {
                Result result = new Result(leaderboardRepresentation[i], Long.valueOf(leaderboardRepresentation[i+1]));
                leaderboard.addResult(result);
            }
            results.add(leaderboard);
        }
        return results;
    }
}
