package gameOfLife;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameOfLifeCellTest {

    GameOfLifeCell testCellOne = new GameOfLifeCell(1,1,true);
    GameOfLifeCell testCellTwo = new GameOfLifeCell(2,2,false);

    GameOfLifeCell[][] game = new GameOfLifeCell[15][15];
    GameOfLife gameOfLife = GameOfLife.getInstance();

    @Before
    public void setUp() throws Exception {
        gameOfLife.setGameOfLife(game);
    }

    @Test
    public void isAlive() {
        assertEquals(testCellOne.isAlive(),true);
        assertEquals(testCellTwo.isAlive(),false);

    }

    @Test
    public void getState() {
        for (int i = 0; i < game.length;i++){
            for (int j = 0; j < game[0].length;j++){
                game[i][j] = new GameOfLifeCell(i,j,false);
            }
        }

        // test for overpopulation
        game[2][4].alive = true;
        game[1][4].alive = true;
        game[3][4].alive = true;
        game[2][3].alive = true;
        game[2][5].alive = true;
        game[2][4].getNearbyLiving();
        game[2][4].getState();

        assertEquals(game[2][4].isAlive(),false);

        //test for loneliness
        game[2][4].alive = true;
        game[1][4].alive = false;
        game[3][4].alive = false;
        game[2][3].alive = false;
        game[2][5].alive = false;
        game[2][4].getNearbyLiving();
        game[2][4].getState();

        assertEquals(game[2][4].isAlive(),false);

        // test for surviving 3 neighbours
        game[2][4].alive = true;
        game[1][4].alive = true;
        game[3][4].alive = true;
        game[2][3].alive = true;
        game[2][5].alive = false;
        game[2][4].getNearbyLiving();
        game[2][4].getState();

        assertEquals(game[2][4].isAlive(),true);

        //test fpr surviving 2 neighbours
        game[2][4].alive = true;
        game[1][4].alive = true;
        game[3][4].alive = true;
        game[2][3].alive = true;
        game[2][5].alive = false;
        game[2][4].getNearbyLiving();
        game[2][4].getState();

        assertEquals(game[2][4].isAlive(),true);

        // test for resurrection
        game[2][4].alive = false;
        game[1][4].alive = true;
        game[3][4].alive = true;
        game[2][3].alive = true;
        game[2][5].alive = false;
        game[2][4].getNearbyLiving();
        game[2][4].getState();

        assertEquals(game[2][4].isAlive(),true);



    }
}