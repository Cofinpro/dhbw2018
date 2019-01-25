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
                gf[row][column].setAlive(false);
            }
        }
// test for overpopulation
        gf[2][4].setAlive(true);
        gf[1][4].setAlive(true);
        gf[3][4].setAlive(true);
        gf[2][3].setAlive(true);
        gf[2][5].setAlive(true);
        gf[2][4].countLivingNeighbors();
        gf[2][4].checkAlive();

        assertFalse(gf[2][4].isAlive());

        //test for loneliness
        gf[2][4].setAlive(true);
        gf[1][4].setAlive(false);
        gf[3][4].setAlive(false);
        gf[2][3].setAlive(false);
        gf[2][5].setAlive(false);
        gf[2][4].countLivingNeighbors();
        gf[2][4].checkAlive();

        assertFalse(gf[2][4].isAlive());

        // test for surviving 3 neighbours
        gf[2][4].setAlive(true);
        gf[1][4].setAlive(true);
        gf[3][4].setAlive(true);
        gf[2][3].setAlive(true);
        gf[2][5].setAlive(false);
        gf[2][4].countLivingNeighbors();
        gf[2][4].checkAlive();

        assertTrue(gf[2][4].isAlive());

        //test fpr surviving 2 neighbours
        gf[2][4].setAlive(true);
        gf[1][4].setAlive(true);
        gf[3][4].setAlive(true);
        gf[2][3].setAlive(true);
        gf[2][5].setAlive(false);
        gf[2][4].countLivingNeighbors();
        gf[2][4].checkAlive();

        assertTrue(gf[2][4].isAlive());

        // test for resurrection
        gf[2][4].setAlive(false);
        gf[1][4].setAlive(true);
        gf[3][4].setAlive(true);
        gf[2][3].setAlive(true);
        gf[2][5].setAlive(false);
        gf[2][4].countLivingNeighbors();
        gf[2][4].checkAlive();

        assertTrue(gf[2][4].isAlive());

    }

    @Test
    void countLivingNeighbors() {
        GameOfLife.getInstance().setGameField(10, 10);
        GameOfLifeCell[][] gf = GameOfLife.getGameField();
        for (int row = 0; row < gf.length; row++) {
            for (int column = 0; column < gf[row].length; column++) {
                gf[row][column] = new GameOfLifeCell(row, column);
                gf[row][column].setAlive(false);
            }
        }
        gf[4][4].countLivingNeighbors();
        assertEquals(0, gf[4][4].getLivingNeighbors());

//###########################################################################################################

        gf[4][4].setLivingNeighbors(0);
        gf[3][3].setAlive(true);
        gf[3][4].setAlive(true);
        gf[3][5].setAlive(true);
        gf[4][3].setAlive(true);
        gf[4][5].setAlive(true);
        gf[5][3].setAlive(true);
        gf[5][4].setAlive(true);
        gf[5][5].setAlive(true);
        gf[4][4].countLivingNeighbors();

        assertEquals(8, gf[4][4].getLivingNeighbors());
    }
}