package model;

public class Game {

    private static Game instance;
    private int rows = 9;
    private int columns = 9;
    private int amountOfBombs;
    private int amountOfRevealed;
    private int amountOfSuspicious;
    private Cell[][] cells;

    public enum GameState {
        START,LOST, WON
    }

    private static GameState gameState = GameState.START;

    public static GameState getState(){
        return gameState;
    }


    public static void setState(GameState state){
        gameState = state;
    }

    public static Game getInstance(){
        if (instance == null){
            instance = new Game();
        }
        return instance;
    }

    private Game(){
        cells = new Cell[rows][columns];
    }
    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getAmountOfBombs() {
        return amountOfBombs;
    }

    public int getAmountOfRevealed() {
        return amountOfRevealed;
    }

    public int getAmountOfSuspicious() {
        return amountOfSuspicious;
    }
    public int getAmountOfCells(){
        return rows * columns;
    }
    public void loseGame(GameState state){
        setState(GameState.LOST);
    }
    public void winGame(GameState state){
        setState(GameState.WON);
    }


}
