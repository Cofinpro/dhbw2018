import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeCellTest {

    @Test
    void checkAlive() {
        GameOfLife.getInstance().setGameField(10,10);
        GameOfLifeCell[][] gf = GameOfLife.getGameField();
        for (int row = 0; row < gf.length; row++) {
            for (int column = 0; column < gf[row].length; column++) {
                gf[row][column] = new GameOfLifeCell(row, column);
                gf[row][column].alive = false;
            }
        }
// test for overpopulation
        gf[2][4].alive = true;
        gf[1][4].alive = true;
        gf[3][4].alive = true;
        gf[2][3].alive = true;
        gf[2][5].alive = true;
        gf[2][4].countLivingNeighbors();
        gf[2][4].checkAlive();

        assertFalse(gf[2][4].alive);

        //test for loneliness
        gf[2][4].alive = true;
        gf[1][4].alive = false;
        gf[3][4].alive = false;
        gf[2][3].alive = false;
        gf[2][5].alive = false;
        gf[2][4].countLivingNeighbors();
        gf[2][4].checkAlive();

        assertFalse(gf[2][4].alive);

        // test for surviving 3 neighbours
        gf[2][4].alive = true;
        gf[1][4].alive = true;
        gf[3][4].alive = true;
        gf[2][3].alive = true;
        gf[2][5].alive = false;
        gf[2][4].countLivingNeighbors();
        gf[2][4].checkAlive();

        assertTrue(gf[2][4].alive);

        //test fpr surviving 2 neighbours
        gf[2][4].alive = true;
        gf[1][4].alive = true;
        gf[3][4].alive = true;
        gf[2][3].alive = true;
        gf[2][5].alive = false;
        gf[2][4].countLivingNeighbors();
        gf[2][4].checkAlive();

        assertTrue(gf[2][4].alive);

        // test for resurrection
        gf[2][4].alive = false;
        gf[1][4].alive = true;
        gf[3][4].alive = true;
        gf[2][3].alive = true;
        gf[2][5].alive = false;
        gf[2][4].countLivingNeighbors();
        gf[2][4].checkAlive();

        assertTrue(gf[2][4].alive);

    }

    @Test
    void countLivingNeighbors() {
        GameOfLife.getInstance().setGameField(10, 10);
        GameOfLifeCell[][] gf = GameOfLife.getGameField();
        for (int row = 0; row < gf.length; row++) {
            for (int column = 0; column < gf[row].length; column++) {
                gf[row][column] = new GameOfLifeCell(row, column);
                gf[row][column].alive = false;
            }
        }
        gf[4][4].countLivingNeighbors();
        assertEquals(0, gf[4][4].livingNeighbors);

//###########################################################################################################

        gf[4][4].livingNeighbors = 0;
        gf[3][3].alive = gf[3][4].alive = gf[3][5].alive = gf[4][3].alive = gf[4][5].alive = gf[5][3].alive
                = gf[5][4].alive = gf[5][5].alive = true;
        gf[4][4].countLivingNeighbors();

        assertEquals(8, gf[4][4].livingNeighbors);
    }
}