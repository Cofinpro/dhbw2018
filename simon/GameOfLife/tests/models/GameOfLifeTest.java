package models;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameSituationTest {

    /**
     * This test requires the Test equals to pass
     * This test has been designed by incrementing a game situation 16 times and analysing the result. I have used https://playgameoflife.com/ to prove its correctness.
     */
    @Test
    void next() {
        GameSituation gameSituation = new GameSituation(15, 15);
        gameSituation.switchCell(6, 7);
        gameSituation.switchCell(7,6);
        gameSituation.switchCell(7, 7);
        gameSituation.switchCell(7,8);
        gameSituation.switchCell(8,6);
        gameSituation.switchCell(8,8);
        gameSituation.switchCell(9,7);
        GameSituation expected = new GameSituation(15, 15);
        expected.switchCell(1,7);
        expected.switchCell(2,6);
        expected.switchCell(2,8);
        expected.switchCell(3,6);
        expected.switchCell(3,8);
        expected.switchCell(4,7);
        expected.switchCell(6,2);
        expected.switchCell(6,3);
        expected.switchCell(6,11);
        expected.switchCell(6,12);
        expected.switchCell(7,1);
        expected.switchCell(7,4);
        expected.switchCell(7,10);
        expected.switchCell(7,13);
        expected.switchCell(8,2);
        expected.switchCell(8,3);
        expected.switchCell(8,11);
        expected.switchCell(8,12);
        expected.switchCell(10,7);
        expected.switchCell(11,6);
        expected.switchCell(11,8);
        expected.switchCell(12,6);
        expected.switchCell(12,8);
        expected.switchCell(13,7);

        //16 Schritte
        for (int i = 0; i < 16; i++) {
            gameSituation = gameSituation.getNextGameSituation();
        }
        assertEquals(expected, gameSituation);
    }

    @Test
    void equals() {
        GameSituation original = new GameSituation(15, 15);
        original.switchCell(7,7);
        original.switchCell(9,10);

        GameSituation same = new GameSituation(15, 15);
        same.switchCell(7, 7);
        same.switchCell(9, 10);
        assertEquals(original, same);

        Object sameAsObject = same;
        assertEquals(original, sameAsObject); //same as object is still the same

        GameSituation slightlyDifferent = new GameSituation(16, 15);
        slightlyDifferent.switchCell(7, 7);
        slightlyDifferent.switchCell(9, 10);
        assertNotEquals(original, slightlyDifferent);
        slightlyDifferent = new GameSituation(15, 16);
        slightlyDifferent.switchCell(7, 7);
        slightlyDifferent.switchCell(9, 10);
        assertNotEquals(original, slightlyDifferent);

        slightlyDifferent = new GameSituation(15, 15);
        slightlyDifferent.switchCell(7, 7);
        slightlyDifferent.switchCell(9, 10);
        slightlyDifferent.switchCell(14,14);
        assertNotEquals(original, slightlyDifferent);
    }
}