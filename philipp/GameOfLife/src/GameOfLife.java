import java.util.Scanner;


public final class GameOfLife {

    private static GameOfLife instance;
    private static GameOfLifeCell[][] gameField;
    static Scanner scanner = new Scanner(System.in);

    private GameOfLife(){ //singleton --> private constructor

    }

    public static void main(String[] args) {
        System.out.println("************************");
        System.out.println("Welcome to Game of Life!");
        System.out.println("************************");
        System.out.println("Press w to play in window mode");
        char window = scanner.next().charAt(0);

        if(window == 'w'){
            LaunchGOL.main(args);
        }
        else {
            getInstance().initializeConsole();
            getInstance().playOnConsole();
        }
    }
    public void setGameField(int width, int length){
        this.gameField = new GameOfLifeCell[width][length];
    }
    public static GameOfLifeCell[][] getGameField() {
        return gameField;
    }

    private void initializeConsole(){
        gameField = new GameOfLifeCell[15][15];

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

    private void playOnConsole(){
        getInstance().printGame();

        char triggerNextIteration = 'y';
        while(triggerNextIteration == 'y'){
            System.out.println("_______________________________");
            getInstance().nextIteration();
            getInstance().printGame();

            System.out.print("Next Iteration? (y): ");
            triggerNextIteration = scanner.next().charAt(0);
        }
    }

    private void printGame(){
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
