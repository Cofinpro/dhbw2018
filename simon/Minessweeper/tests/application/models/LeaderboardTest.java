package application.models;

import application.enums.Difficulty;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeaderboardTest {

    private Leaderboard one;
    private Leaderboard two;
    private Leaderboard three;

    @Before
    public void setUp() {
        one = new Leaderboard(Difficulty.EASY);
        two = new Leaderboard(Difficulty.EASY);
        three = new Leaderboard(Difficulty.MEDIUM);
    }

    @Test
    public void equals() {
        two.addResult(new Result("should not matter", 12L));
        assertEquals(one, two);
        assertNotEquals(one, three);
    }

    @Test
    public void compareTo() {
        assertEquals(0, one.compareTo(two));
        assertTrue(one.compareTo(three) < 0);
        assertTrue(three.compareTo(one) > 0);
    }
}