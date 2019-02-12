package application.models;

import application.enums.Difficulty;
import application.persistance.LeaderboardDao;

import java.util.*;

public class LeaderboardManager {
    private static LeaderboardManager ourInstance = new LeaderboardManager();
    private SortedSet<Leaderboard> leaderboards;

    public static LeaderboardManager getInstance() {
        return ourInstance;
    }

    private LeaderboardManager() {
        leaderboards = LeaderboardDao.getLeaderboards();
    }

    public boolean handleNewResult(Result result) {
        Difficulty difficulty = Settings.getInstance().getDifficulty();
        Optional<Leaderboard> leaderboardOptional = leaderboards.stream().filter(l -> l.getDifficulty() == difficulty).findFirst();
        Leaderboard leaderboard = leaderboardOptional.isPresent() ? leaderboardOptional.get() : null;
        if (leaderboard == null) {
            leaderboards.add((leaderboard = new Leaderboard(difficulty)));
        }
        return leaderboard.addResult(result);
    }

    public SortedSet<Leaderboard> getLeaderboards() {
        return leaderboards;
    }
}