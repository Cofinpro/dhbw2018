package BowlingSpiel;


import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class BowlingGameTest {
    int count = 10;
    BowlingGame game = new BowlingGame(count);

    @org.junit.Test
    public void reset() {
        game.knockDownEverything();
        game.reset();
        assertEquals(game.countStandingPins(), count);
    }

    /**
     * random Method cant be tested because you have no expected value
     */
    @org.junit.Test
    public void rollBall() {

    }

    @org.junit.Test
    public void countStandingPins() {
        game.knockDownEverything();
        assertEquals(game.countStandingPins(),0);
        game.reset();
        assertEquals(game.countStandingPins(),count);
    }


    @org.junit.Test
    public void visualizeStandingPins() {
    }

}