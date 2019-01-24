import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.Scanner;


public final class GameOfLife {

    private static GameOfLife instance;
    private GameOfLifeCell[][] gameField;
    private static Scanner scanner = new Scanner(System.in);
    private ArrayList<GameOfLifeCell[][]> iterationList = new ArrayList<>();

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

  /*  @Override
    public GameOfLife clone(){

        return null;
    } */
    public void setGameField(int width, int length){
        gameField = new GameOfLifeCell[width][length];
    }
    public static GameOfLifeCell[][] getGameField() {
        return getInstance().gameField;
    }

    private void initializeConsole(){
        gameField = new GameOfLifeCell[15][15];

        for (int row = 0; row < gameField.length; row++) {
            for (int column = 0; column < gameField[row].length; column++) {
                gameField[row][column] = new GameOfLifeCell(row, column);
                gameField[row][column].setAlive(false);
            }
        }
        gameField[3][4].setAlive(true);
        gameField[3][5].setAlive(true);
        gameField[3][6].setAlive(true);
        gameField[4][6].setAlive(true);
        gameField[4][3].setAlive(true);
        gameField[8][8].setAlive(true);
        gameField[14][14].setAlive(true);
    }

    public void nextIteration(){
        GameOfLifeCell[][] gfIteration = new GameOfLifeCell[gameField.length][gameField[0].length];
        for (int row = 0; row < gameField.length; row++) {
            for (int column = 0; column < gameField[row].length; column++) {
                gameField[row][column].countLivingNeighbors();
                gfIteration[row][column] = new GameOfLifeCell(row, column, gameField[row][column].getLivingNeighbors(), gameField[row][column].isAlive());
            }
        }
        iterationList.add(gfIteration); //gfIteration is copy of gameField (copy in for loop --> not the same references)

        for (GameOfLifeCell[] gameOfLifeCells : gameField) {
            for (GameOfLifeCell gameOfLifeCell : gameOfLifeCells) {
                gameOfLifeCell.checkAlive();
            }
        }
    }

    public void previousIteration(){
        if(iterationList.size() > 0) {
            gameField = iterationList.get(iterationList.size() - 1);
            iterationList.remove(iterationList.size()-1);
        }
        else{
            Alert noPrevIterAlert = new Alert(Alert.AlertType.ERROR);
            noPrevIterAlert.setTitle("Error");
            noPrevIterAlert.setHeaderText("No previous iteration");
            noPrevIterAlert.setContentText("The current iteration was the first iteration.\n" +
                    "You cant go back any further.");
            noPrevIterAlert.showAndWait();
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
        for (GameOfLifeCell[] gameOfLifeCells : gameField) {
            for (GameOfLifeCell gameOfLifeCell : gameOfLifeCells) {
                if (gameOfLifeCell.isAlive())
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
