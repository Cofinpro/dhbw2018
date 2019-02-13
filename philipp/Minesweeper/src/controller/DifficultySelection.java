package controller;

import model.Difficulty;
import model.GameSettings;
import view.UserSelection;

public class DifficultySelection {
    private static GameSettings gameSettings = GameSettings.getInstance();

    public static void askForValues() {
        Object[] options = {"Easy Cheasy",
                "Normal",
                "Hard",
                "Sicko Mode",
                "I surrender"};

        int n = UserSelection.selectDifficulty("Choose Difficulty Level",
                "Please select a difficulty", options);

        //todo: automatically display all difficulties from enum with: Difficulty.values()

        switch (n) {
            case 0:
                gameSettings.setDifficulty(Difficulty.EASY_CHEASY);
                break;
            case 1:
                gameSettings.setDifficulty(Difficulty.NORMAL);
                break;
            case 2:
                gameSettings.setDifficulty(Difficulty.HARD);
                break;
            case 3:
                gameSettings.setDifficulty(Difficulty.SICKO_MODE);
                break;
            case 4:
                System.exit(0);
        }
    }

}
