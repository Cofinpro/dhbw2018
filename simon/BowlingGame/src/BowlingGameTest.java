import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

class BowlingGameTest {

    @Test
    void reset() {
        int pinCount = 33;
        BowlingGame bg = new BowlingGame(pinCount, 1.0);
        bg.rollBall();
        Assert.assertThat(bg.countStandingPins(), is(0));
        bg.reset();
        Assert.assertThat(bg.countStandingPins(), is(pinCount));
    }

    @Test
    void getRowCount() {
        BowlingGame bg = new BowlingGame(10,1);
        int expectedRowCount = 4;
        int actualRowCount = bg.getRowCount();
        Assert.assertThat(actualRowCount, is(expectedRowCount));

        bg = new BowlingGame(11, 1);
        expectedRowCount = 5;
        actualRowCount = bg.getRowCount();
        Assert.assertThat(actualRowCount, is(expectedRowCount));

        bg = new BowlingGame(15, 1);
        expectedRowCount = 5;
        actualRowCount = bg.getRowCount();
        Assert.assertThat(actualRowCount, is(expectedRowCount));
    }
}