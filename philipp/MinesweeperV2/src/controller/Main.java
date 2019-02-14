package controller;

import model.Board;
import model.Difficulty;
import model.GameSettings;
import view.GameView;

public class Main {

    private static GameSettings gameSettings = GameSettings.getInstance();
    private static Board board = Board.getInstance();

    public static void main(String[] args) {
        setDifficulty();
        board.resetGame();
        GameView.getInstance().initializeGameView();
    }

    private static void setDifficulty() {
        Difficulty[] difficulties = Difficulty.values();
        String[] difNames = new String[difficulties.length];
        for (int i = 0; i < difficulties.length; i++) {
            difNames[i] = difficulties[i].getDifName();
        }

        int n = UserSelectionController.getUserChoice("Choose Difficulty Level",
                "Please select a difficulty", difNames);

        gameSettings.setDifficulty(difficulties[n]);
    }
}
