package application.persistance;

import application.enums.Difficulty;
import application.models.Leaderboard;
import application.models.LeaderboardManager;
import application.models.Result;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class LeaderboardDaoTest {

    private static final String PATH = "testResources\\leaderboards.csv";
    private Leaderboard[] expectedLeaderboards;
    private Leaderboard[] actualLeaderboards;

    @Before
    public void setUp() {
        expectedLeaderboards = new Leaderboard[2];
        Leaderboard leaderboardEasy = new Leaderboard(Difficulty.EASY);
        Leaderboard leaderboardMedium = new Leaderboard(Difficulty.MEDIUM);
        leaderboardEasy.addResult(new Result("Simon", 12L));
        leaderboardEasy.addResult(new Result("Elias", 42L));
        leaderboardMedium.addResult(new Result("Tobias", 33L));
        leaderboardMedium.addResult(new Result("Jonas", 66L));
        expectedLeaderboards[0] = leaderboardEasy;
        expectedLeaderboards[1] = leaderboardMedium;
        LeaderboardDao.save(PATH, new TreeSet(Arrays.asList(expectedLeaderboards)));
    }

    @Test
    public void getLeaderboards() {
        actualLeaderboards = new Leaderboard[0];
        actualLeaderboards = LeaderboardDao.getLeaderboards(PATH).toArray(actualLeaderboards);
        assertArrayEquals(expectedLeaderboards, actualLeaderboards);
        for (int i = 0; i < actualLeaderboards.length; i++) {
            Leaderboard actualLeaderboard = actualLeaderboards[i];
            Leaderboard expectedLeaderboard = expectedLeaderboards[i];
            Object[] actualResults = actualLeaderboard.getResults().toArray();
            Object[] expectedResults = expectedLeaderboard.getResults().toArray();
            assertArrayEquals(expectedResults, actualResults);
        }
    }
}