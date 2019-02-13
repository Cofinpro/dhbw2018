package controller;

import model.Board;
import view.Game;

public class Main {
    public static void main(String[] args) {
        DifficultySelection.askForValues();
        Game.getInstance().initializeView();
    }
}
