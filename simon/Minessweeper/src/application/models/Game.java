package application.models;

import application.enums.GameState;
import application.helper.RandomHelper;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Random;

public class Game {
    private static Game ourInstance = new Game();
    private GameCell[][] gameCells;
    private SimpleObjectProperty<GameState> gameStateProperty;
    private SimpleIntegerProperty revealedCellCountProperty;

    public static Game getInstance() {
        return ourInstance;
    }

    private Game() {
        gameCells = new GameCell[1][0]; //one, so that gameCells[0].length works
        gameStateProperty = new SimpleObjectProperty<>(GameState.PLAYING);
        revealedCellCountProperty = new SimpleIntegerProperty(0);
        revealedCellCountProperty.addListener(event -> {
            if (getGameState() != GameState.LOST && getRevealedCellCount() == getCellCount() - Settings.getInstance().getDifficulty().getBombCount()) {
                setGameState(GameState.WON);
            }
        });
    }

    private void setGameState(GameState gameState) {
        gameStateProperty.set(gameState);
    }

    public void setup() {
        gameCells = RandomHelper.getGameField(this);
        gameStateProperty.set(GameState.PLAYING);
        revealedCellCountProperty.set(0);
    }

    public int getRevealedCellCount() {
        return revealedCellCountProperty.get();
    }

    public int getRowCount() {
        return gameCells.length;
    }

    public int getColumnCount() {
        return gameCells[0].length;
    }

    private int getCellCount() {
        return getRowCount() * getColumnCount();
    }

    public GameCell getGameCell(int row, int column) {
        return gameCells[row][column];
    }

    void loseGame() {
        gameStateProperty.set(GameState.LOST);
    }

    public GameState getGameState() {
        return  gameStateProperty.get();
    }

    public SimpleObjectProperty<GameState> getGameStateProperty() {
        return gameStateProperty;
    }

    public SimpleIntegerProperty getRevealedCellCountProperty() {
        return revealedCellCountProperty;
    }
}
