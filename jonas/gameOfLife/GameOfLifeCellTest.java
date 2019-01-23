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

    //tests if the isAlive method returns alive cells as alive and dead cells as dead
    @Test
    public void isAlive() {
        assertEquals(testCellOne.isAlive(),true);
        assertEquals(testCellTwo.isAlive(),false);

    }

    //tests if the getNearbyLiving method returns the right amount of living cells
    @Test
    public void getNearbyLiving(){
        for (int i = 0; i < game.length;i++){
            for (int j = 0; j < game[0].length;j++){
                game[i][j] = new GameOfLifeCell(i,j,false);
            }
        }
        game[1][1].alive = true;
        game[2][1].alive = true;
        game[3][1].alive = true;
        game[1][2].alive = true;
        game[2][2].alive = true;
        game[3][2].alive = true;
        game[1][3].alive = true;
        game[2][3].alive = true;
        game[3][3].alive = true;
        assertEquals(game[2][2].getNearbyLiving(),8);


        game[1][1].alive = false;
        game[2][1].alive = false;
        game[3][1].alive = false;
        game[1][2].alive = false;
        game[2][2].alive = true;
        game[3][2].alive = false;
        game[1][3].alive = false;
        game[2][3].alive = false;
        game[3][3].alive = false;
        assertEquals(game[2][2].getNearbyLiving(), 0);
    }

    //tests if the getState Method follows the rules of the GameOfLife Game
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

        // test for surviving with 3 neighbours
        game[2][4].alive = true;
        game[1][4].alive = true;
        game[3][4].alive = true;
        game[2][3].alive = true;
        game[2][5].alive = false;
        game[2][4].getNearbyLiving();
        game[2][4].getState();

        assertEquals(game[2][4].isAlive(),true);

        //test for surviving with 2 neighbours
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