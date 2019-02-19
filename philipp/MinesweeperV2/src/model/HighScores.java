package model;

import java.util.ArrayList;

public class HighScores {
    private static HighScores ourInstance;
    private static ArrayList<String[]> usersWithHighscores = new ArrayList<>();

    public static HighScores getInstance() {
        if (ourInstance == null)
            ourInstance = new HighScores();
        return ourInstance;
    }

    private HighScores() {
        String[] testUserWithHighScore = {"Phape", "42"};
        String[] testUserWithHighScore1 = {"Phape", "42"};
        String[] testUserWithHighScore2 = {"Phape", "42"};

        usersWithHighscores.add(testUserWithHighScore);
        usersWithHighscores.add(testUserWithHighScore1);
        usersWithHighscores.add(testUserWithHighScore2);

    }

    public ArrayList<String[]> getUsersWithHighscores() {
        return usersWithHighscores;
    }
}
