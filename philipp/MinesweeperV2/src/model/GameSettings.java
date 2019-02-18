package model;

public class GameSettings {
    //this is were the Properties of the current game are saved
    //eg. the difficulty

    private static GameSettings instance;
    private Difficulty difficulty;
    private int amountSuperMines;

    private GameSettings() {

    }

    public static GameSettings getInstance() {
        if (instance == null)
            instance = new GameSettings();

        return instance;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setAmountSuperMines(int amountSuperMines) {
        this.amountSuperMines = amountSuperMines;
    }

    public int getAmountSuperMines() {
        return amountSuperMines;
    }

}
