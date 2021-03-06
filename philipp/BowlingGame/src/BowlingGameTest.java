import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {

    /**  int testPinsAmount = 10;
     int testPinsAmount1 = 50;
     int testPinsAmount2 = -1;
     int testPinsamount3 = 100;
     private Pin[] pinArray;

     @BeforeEach
     void setUp() {
     //arrange

     BowlingGame bowlingGame = new BowlingGame(testPinsAmount);
     BowlingGame bowlingGame1 = new BowlingGame(testPinsAmount1);
     BowlingGame bowlingGame2 = new BowlingGame(testPinsAmount2);
     BowlingGame bowlingGame3 = new BowlingGame(testPinsamount3);
     **/


    @Test
    void reset() {
        //arrange
        BowlingGame bowlingGame = new BowlingGame(3);
        bowlingGame.rollBall(100);
        bowlingGame.reset();

        //act
        String amountPutUpPins = bowlingGame.countStandingPins();

        //assert
        assertEquals(amountPutUpPins, "3 pin/s are still standing", "Not all pins are standing");
    }

    @Test
    void rollBall() {
        // this method knocks down a random amount of pins
        // you cant expect a certan number to be returned
        // therefore there is no right or wrong for this method
    }

    @Test
    void countStandingPins() {
        BowlingGame bowlingGame = new BowlingGame(10);
        String amountPinsUp = bowlingGame.countStandingPins();
        bowlingGame.rollBall(100);
        String amountPinsUp2 = bowlingGame.countStandingPins();

        assertEquals(amountPinsUp, "10 pin/s are still standing", "countStandingPins not working properly");
        assertEquals(amountPinsUp2, "0 pin/s are still standing", "countStandingPins not working properly");

    }

    @Test
    void visualizeStandingPins() {
        // hard to write a test method for the visualization
    }

}