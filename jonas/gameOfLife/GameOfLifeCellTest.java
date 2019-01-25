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
        for (int i = 1; i <4;i++){
            for (int j = 1; j < 4;j++){
                game[i][j].setAlive(true);
            }
        }
        assertEquals(game[2][2].getNearbyLiving(),8);

        for (int i = 1; i <4;i++){
            for (int j = 1; j < 4;j++){
                game[i][j].setAlive(false);
            }
        }

        game[2][2].setAlive(true);
        assertEquals(game[2][2].getNearbyLiving(), 0);
    }

    //tests if the getState Method follows the rules of the GameOfLife Game
    @Test
    public void updateState() {
        for (int i = 0; i < game.length;i++){
            for (int j = 0; j < game[0].length;j++){
                game[i][j] = new GameOfLifeCell(i,j,false);
            }
        }

        // test for overpopulation
        game[2][4].setAlive(true);
        game[1][4].setAlive(true);
        game[3][4].setAlive(true);
        game[2][3].setAlive(true);
        game[2][5].setAlive(true);
        game[2][4].getNearbyLiving();
        game[2][4].updateState();

        assertEquals(game[2][4].isAlive(),false);

        //test for loneliness
        game[2][4].setAlive(true);
        game[1][4].setAlive(false);
        game[3][4].setAlive(false);
        game[2][3].setAlive(false);
        game[2][5].setAlive(false);
        game[2][4].getNearbyLiving();
        game[2][4].updateState();

        assertEquals(game[2][4].isAlive(),false);

        // test for surviving with 3 neighbours
        game[2][4].setAlive(true);
        game[1][4].setAlive(true);
        game[3][4].setAlive(true);
        game[2][3].setAlive(true);
        game[2][5].setAlive(false);
        game[2][4].getNearbyLiving();
        game[2][4].updateState();

        assertEquals(game[2][4].isAlive(),true);

        //test for surviving with 2 neighbours
        game[2][4].setAlive(true);
        game[1][4].setAlive(true);
        game[3][4].setAlive(true);
        game[2][3].setAlive(false);
        game[2][5].setAlive(false);
        game[2][4].getNearbyLiving();
        game[2][4].updateState();

        assertEquals(game[2][4].isAlive(),true);

        // test for resurrection
        game[2][4].setAlive(false);
        game[1][4].setAlive(true);
        game[3][4].setAlive(true);
        game[2][3].setAlive(true);
        game[2][5].setAlive(false);
        game[2][4].getNearbyLiving();
        game[2][4].updateState();

        assertEquals(game[2][4].isAlive(),true);
    }

}