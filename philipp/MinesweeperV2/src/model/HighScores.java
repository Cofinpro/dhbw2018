package model;

import java.util.ArrayList;

public class HighScores {
    private static HighScores ourInstance = new HighScores();
    private static ArrayList<String[]> usersWithHighscores = new ArrayList<>();

    public static HighScores getInstance() {
        return ourInstance;
    }

    private HighScores() {
        String[] testUserWithHighScore = {"Phape", "42"};
        usersWithHighscores.add(testUserWithHighScore);
    }

    public ArrayList<String[]> getUsersWithHighscores() {
        return usersWithHighscores;
    }
}
