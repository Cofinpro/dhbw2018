import java.util.Scanner;

public final class GameOfLife {

    private static GameOfLife instance;
    GameOfLifeCell[][] gameField = new GameOfLifeCell[15][15];

    private GameOfLife(){ //singleton --> private constructor

    }

    public static void main(String[] args) {
        getInstance().initialize();
        getInstance().printGame();

        Scanner scanner = new Scanner(System.in);
        char triggerNextIteration = 'y';
        while(triggerNextIteration == 'y'){
            System.out.println("_______________________________");
            getInstance().nextIteration();
            getInstance().printGame();

            System.out.print("Next Iteration? (y): ");
            triggerNextIteration = scanner.next().charAt(0);
        }
    }

    public void initialize(){
        for (int row = 0; row < gameField.length; row++) {
            for (int column = 0; column < gameField[row].length; column++) {
                gameField[row][column] = new GameOfLifeCell(row, column);
                gameField[row][column].alive = false;
            }
        }
        gameField[3][4].alive = true;
        gameField[3][5].alive = true;
        gameField[3][6].alive = true;
        gameField[4][6].alive = true;
        gameField[4][3].alive = true;
        gameField[8][8].alive = true;
        gameField[14][14].alive = true;
    }

    public void nextIteration(){
        for (int row = 0; row < gameField.length; row++) {
            for (int column = 0; column < gameField[row].length; column++)
                gameField[row][column].countLivingNeighbors();
        }
        for (int row = 0; row < gameField.length; row++) {
            for (int column = 0; column < gameField[row].length; column++)
                gameField[row][column].checkAlive();
        }
    }

    public void printGame(){
        for (int row = 0; row < gameField.length; row++){
            for (int column = 0; column < gameField[row].length; column++){
                if (gameField[row][column].alive)
                    System.out.print("\u2665 ");
                else
                    System.out.print("\u2620 ");
            }
            System.out.println();
        }
    }

    public static GameOfLife getInstance(){
        if (instance == null)
            instance = new GameOfLife();
        return instance;
    }
}
