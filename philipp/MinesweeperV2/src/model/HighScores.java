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
        String[] userWithHighScoreAndDifficulty = new String[3];
        userWithHighScoreAndDifficulty[0] = GameSettings.getInstance().getDifficulty().getDifName();
        userWithHighScoreAndDifficulty[1] = userWithHighScore[0];
        userWithHighScoreAndDifficulty[2] = userWithHighScore[1];
        usersWithHighscores.add(userWithHighScoreAndDifficulty);
    }

    public ArrayList<String[]> getUsersWithHighscoresAtCurrentDifficulty() {
        ArrayList<String[]> currentDifficultyHighScoreCollection = new ArrayList<>();

        for (String[] stringArray : usersWithHighscores) {
            if (stringArray[0].equals(GameSettings.getInstance().getDifficulty().getDifName()))
                currentDifficultyHighScoreCollection.add(stringArray);
        }
        return currentDifficultyHighScoreCollection;
    }

    public ArrayList<String[]> getUsersWithHighscores() {
        return usersWithHighscores;
    }
}
