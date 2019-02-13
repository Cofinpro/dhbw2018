package model;

public class GameSettings {
    //this is were the Properties of the current game are saved
    //eg. the difficulty

    private static GameSettings instance;
    private static Difficulty difficulty;

    private GameSettings() {

    }

    public static GameSettings getInstance() {
        if (instance == null)
            instance = new GameSettings();

        return instance;
    }

    public void setDifficulty(Difficulty difficulty) {
        GameSettings.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

}
