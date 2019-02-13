package application.models;

import application.enums.Difficulty;
import application.persistance.LeaderboardDao;
import javafx.beans.property.SimpleObjectProperty;

import java.util.*;

public class LeaderboardManager {
    private static LeaderboardManager ourInstance = new LeaderboardManager();
    private SortedSet<Leaderboard> leaderboards;
    private Result bestResultOfCurrentPlayer;
    private Result recentResult;

    public static LeaderboardManager getInstance() {
        return ourInstance;
    }

    private LeaderboardManager() {
        leaderboards = LeaderboardDao.getLeaderboards();
    }

    public void handleNewResult(Result result) {
        recentResult = result;
        Difficulty difficulty = Settings.getInstance().getDifficulty();
        Leaderboard leaderboard = getLeaderboard(difficulty);
        bestResultOfCurrentPlayer = leaderboard.addResult(result);
    }

    public Leaderboard getLeaderboard(Difficulty difficulty) {
        Optional<Leaderboard> leaderboardOptional = leaderboards.stream().filter(l -> l.getDifficulty() == difficulty).findFirst();
        Leaderboard leaderboard = leaderboardOptional.orElse(null);
        if (leaderboard == null) {
            leaderboards.add((leaderboard = new Leaderboard(difficulty)));
        }
        return  leaderboard;
    }

    public SortedSet<Leaderboard> getLeaderboards() {
        return leaderboards;
    }

    public Result getRecentResult() {
        return recentResult;
    }

    public Result getBestResultOfCurrentPlayer() {
        return bestResultOfCurrentPlayer;
    }
}
