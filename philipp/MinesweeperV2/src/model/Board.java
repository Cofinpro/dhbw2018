package model;

import view.GameView;

public class Board {
    //the board includes all tiles

    private static Board instance;

    private Tile[][] tiles;
    private int amountMinesLeft;

    private Board() {

    }

    public static Board getInstance() {
        if (instance == null)
            instance = new Board();
        return instance;
    }

    public void initializeBoard(int x, int y, int amountMinesLeft) {
        this.amountMinesLeft = amountMinesLeft;
        tiles = new Tile[x][y];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new Tile();
            }
        }
    }

    public void switchTileFlag(int x, int y) {
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

    public int getAmountMinesLeft() {
        return amountMinesLeft;
    }
}
