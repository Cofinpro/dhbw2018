package model;

import javafx.beans.property.SimpleIntegerProperty;
import view.GameView;

import java.util.Timer;
import java.util.TimerTask;

public class Board {

    private static Board instance;

    private GameSettings gameSettings = GameSettings.getInstance();
    private Tile[][] tiles;
    private int amountMinesLeft;
    private SimpleIntegerProperty seconds;
    private boolean gameIsRunning = false;

    private Board() {
        seconds = new SimpleIntegerProperty(0);
        createTimer();
    }

    public static Board getInstance() {
        if (instance == null)
            instance = new Board();
        return instance;
    }

    public void resetGame() {
        if (tiles == null) {
            int rows = gameSettings.getDifficulty().getRows();
            int cols = gameSettings.getDifficulty().getCols();
            tiles = new Tile[rows][cols];
        }
        int amountMinesInGame = GameSettings.getInstance().getDifficulty().getAmountMines();
        int amountSuperMines = GameSettings.getInstance().getAmountSuperMines();
        this.amountMinesLeft = amountMinesInGame;

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                tiles[x][y] = new Tile();
            }
        }
        createRandomMines(amountMinesInGame, amountSuperMines);

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                tiles[x][y].setNearbyMines(countNearbyMines(x, y));
            }
        }
        gameIsRunning = false;
    }

    private void createTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                increaseSeconds();
            }
        }, 1000, 1000);

    }

    private void increaseSeconds() {
        if(!checkWin() && gameIsRunning)
            seconds.set(seconds.getValue()+1);

        if(!checkWin() && !gameIsRunning)
            seconds.set(0);
    }

    public SimpleIntegerProperty getSeconds() {
        return seconds;
    }

    private void createRandomMines(int amountMines, int amountSuperMines) {
        for (int i = 0; i < amountSuperMines; i++) {
            setMine(2);
        }
        for (int i = 0; i < amountMines-amountSuperMines; i++) {
            setMine(1);
        }
    }

    private void setMine(int mineLevel) {
        int randX = (int)(Math.random() * tiles.length);
        int randY = (int)(Math.random() * tiles[randX].length);
        if (tiles[randX][randY].getMineLevel() > 0)
            setMine(mineLevel);
        else
            tiles[randX][randY].setMineLevel(mineLevel);
    }

    private int countNearbyMines(int row, int col) {

        if (tiles[row][col].getMineLevel() > 0)
            return -1;

        int mineCount = 0;
        for (int x = row - 1; x <= row + 1; x++) {
            for (int y = col - 1; y <= col + 1; y++) {
                if (isInside(x, y) && tiles[x][y].getMineLevel() > 0)
                    mineCount += tiles[x][y].getMineLevel();
            }
        }
        return mineCount;
    }

    private boolean isInside(int x, int y) {
        return x >= 0 && y >= 0 && x < tiles.length && y < tiles[x].length;
    }

    public void switchTileFlagged(int x, int y) {
        if (tiles[x][y].isFlagged()) {
            tiles[x][y].setFlagged(false);
            GameView.getInstance().changeButtonText(x, y, null);
            amountMinesLeft++;
        }
        else{
            tiles[x][y].setFlagged(true);
            GameView.getInstance().changeButtonText(x, y, tiles[x][y].toString());
            amountMinesLeft--;
        }
    }

    public char revealTile(int x, int y) {
        if(!gameIsRunning)
            gameIsRunning = true;

        tiles[x][y].setRevealed(true);
        if (tiles[x][y].getNearbyMines() == 0)
            openZeros(x, y);
        if (tiles[x][y].getMineLevel() > 0)
            return 'l';
        if (checkWin())
            return  'w';

        return 'n';
    }

    private void openZeros(int row, int col) {
        GameView gameView = GameView.getInstance();
        for (int x = row - 1; x <= row + 1; x++) {
            for (int y = col - 1; y <= col + 1; y++) {
                if (isInside(x, y) && !tiles[x][y].isRevealed()) {
                    gameView.changeButtonText(x, y, tiles[x][y].toString());
                    gameView.setButtonEnabled(x, y, false);
                    tiles[x][y].setRevealed(true);

                    if (tiles[x][y].getNearbyMines() == 0)
                        openZeros(x, y);
                }
            }
        }
    }

    private boolean checkWin() {
        boolean win = true;
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                //if a not-a-mine-tile is unrevealed win is false
                if (!tiles[x][y].isRevealed() && !(tiles[x][y].getMineLevel() > 0))
                    win = false;
            }
        }
        return win;
    }

    public String getTileLabel(int x, int y) {
        if (!tiles[x][y].isFlagged() && !tiles[x][y].isRevealed())
            return null;
        else
            return tiles[x][y].toString();
    }

    public int getAmountMinesLeft() {
        return amountMinesLeft;
    }
}
