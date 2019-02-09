package application.models;

public class GameField {

    private GameCell[][] gameCells;

    public GameField() {
        int fieldLength = Settings.getInstance().getDifficultyProperty().get().getFieldLength();
        gameCells =  new GameCell[fieldLength][fieldLength];
        for (int row = 0; row < gameCells.length; row++) {
            for (int column = 0; column < gameCells[row].length; column++) {
                gameCells[row][column] = new GameCell();
            }
        }
    }

    public int getLength() {
        return gameCells.length;
    }

    public GameCell getGameCell(int row, int column) {
        return gameCells[row][column];
    }
}
