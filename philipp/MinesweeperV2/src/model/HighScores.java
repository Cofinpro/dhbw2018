package model;

import persistance.CsvDao;

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
        CsvDao csvDao = new CsvDao();
        usersWithHighscores = (csvDao.readHighScoresFromCsv());
    }

    public void addHighScore(String[] userWithHighScore) {
        usersWithHighscores.add(userWithHighScore);
    }

    public ArrayList<String[]> getUsersWithHighscores() {
        return usersWithHighscores;
    }
}
