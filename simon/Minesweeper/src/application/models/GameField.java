package application.models;

public class GameField {
    private GameCell[][] gameCells;

    public GameField() {
        gameCells = new GameCell[10][10];
        for (int i = 0; i < gameCells.length; i++) {
            for (int j = 0; j < gameCells[i].length; j++) {
                gameCells[i][j] = new GameCell();
            }
        }
    }

    public int getRowCount() {
        return gameCells.length;
    }

    public int getColumnCount() {
        return gameCells[0].length;
    }

    public GameCell getGameCell(int row, int column) {
        return gameCells[row][column];
    }
}
