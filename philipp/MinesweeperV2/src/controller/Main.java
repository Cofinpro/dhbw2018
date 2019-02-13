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
        int rows = gameSettings.getDifficulty().getRows();
        int cols = gameSettings.getDifficulty().getCols();
        int amountMines = gameSettings.getDifficulty().getAmountMines();
        board.initializeBoard(rows, cols, amountMines);
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
