package controller;

import model.Board;
import view.GameView;
import view.MainFXML;

public class GameViewController {

    private static GameView gameView = GameView.getInstance();
    private static Board board = Board.getInstance();

    public static void handleTileClick(int x, int y, int modifiers) {

        if (modifiers == 17) {
            board.switchTileFlagged(x, y);
            gameView.changeLeftMinesTextField("Mines left: " + board.getAmountMinesLeft());

        }
        else {
            char status = board.revealTile(x, y);

            gameView.changeButtonText(x, y, board.getTileLabel(x, y));
            gameView.setButtonEnabled(x, y, false);
            switch (status) {
                case 'n':
                    break;
                case 'w':
                    gameView.displayWin();
                    MainFXML.main(null);
                    break;
                case 'l':
                    gameView.displayLose();
                    break;
            }
        }
    }

    public static void handleResetClick() {
        board.resetGame();
        gameView.resetGame();
    }
}
