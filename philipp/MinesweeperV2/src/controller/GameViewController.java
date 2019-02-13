package controller;

import model.Board;
import view.GameView;

public class GameViewController {

    private static GameView gameView = GameView.getInstance();

    public static void handleTileClick(int x, int y, int modifiers) {
        Board board = Board.getInstance();
        if (modifiers == 17) {
            board.switchTileFlag(x, y);
            gameView.changeLeftMinesTextField(String.valueOf(board.getAmountMinesLeft()));

        }
        else {
            //open Tile
        }

    }

    public static void handleResetClick() {
        //todo
    }
}
