package model;

import view.GameView;

public class Board {

    private static Board instance;

    private GameSettings gameSettings = GameSettings.getInstance();
    private Tile[][] tiles;
    private int amountMinesLeft;

    private Board() {

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
        this.amountMinesLeft = amountMinesInGame;

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                tiles[x][y] = new Tile();
            }
        }
        createRandomMines(amountMinesInGame);

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                tiles[x][y].setNearbyMines(countNearbyMines(x, y));
            }
        }
    }

    private void createRandomMines(int amountMines) {
        for (int i = 0; i < amountMines; i++) {
            int randX = (int)(Math.random() * tiles.length);
            int randY = (int)(Math.random() * tiles[randX].length);
            if (tiles[randX][randY].getMineLevel() > 0)
                i--;
            else
                tiles[randX][randY].setMineLevel(1);
        }
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
