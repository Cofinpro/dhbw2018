package application.persistance;

import application.enums.Difficulty;
import application.helper.CSVHelper;
import application.models.Leaderboard;
import application.models.LeaderboardManager;
import application.models.Result;

import java.util.*;

public class LeaderboardDao {

    public static final String LEADERBOARD_CSV_PATH = "resources\\users.csv";

    public static void save() {
        Set<Leaderboard> leaderboards = LeaderboardManager.getInstance().getLeaderboards();
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
        CSVHelper.writeCSV(LEADERBOARD_CSV_PATH, leaderboardPresentations);
    }

    public static SortedSet<Leaderboard> getLeaderboards() {
        SortedSet<Leaderboard> results = new TreeSet<>();
        Collection<String[]> leaderboardRepresentations = CSVHelper.readCSV(LEADERBOARD_CSV_PATH);
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
