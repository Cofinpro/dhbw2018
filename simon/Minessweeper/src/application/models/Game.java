package application.models;

import application.enums.GameState;
import application.helper.RandomHelper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Game {
    private static Game ourInstance = new Game();
    private RepresentableGameCell[][] gameCells;
    private SimpleObjectProperty<GameState> gameStateProperty;
    private SimpleIntegerProperty revealedHarmlessCellCountProperty;
    private Long startingTimeMillis;
    private Long finishingTimeMillis;

    public static Game getInstance() {
        return ourInstance;
    }

    private Game() {
        gameCells = new RepresentableGameCell[1][0]; //one, so that gameCells[0].length works
        gameStateProperty = new SimpleObjectProperty<>(GameState.PLAYING);
        revealedHarmlessCellCountProperty = new SimpleIntegerProperty(0);
        revealedHarmlessCellCountProperty.addListener(event -> {
            if (getGameState() != GameState.LOST && getRevealedCellCount() == getCellCount() - Settings.getInstance().getDifficulty().getBombCount()) {
                setGameState(GameState.WON);
                finishingTimeMillis = System.currentTimeMillis();
            }
            if (startingTimeMillis == null) {
                startingTimeMillis = System.currentTimeMillis();
            }
        });
    }

    public long getTimePlayed() {
        if (startingTimeMillis == null) {
            return 0L;
        }
        if (finishingTimeMillis == null) {
            return System.currentTimeMillis() - startingTimeMillis;
        }
        return finishingTimeMillis - startingTimeMillis;
    }

    private void setGameState(GameState gameState) {
        gameStateProperty.set(gameState);
    }

    public void setup() {
        gameCells = RandomHelper.getGameField(this);
        gameStateProperty.set(GameState.PLAYING);
        revealedHarmlessCellCountProperty.set(0);
    }

    public int getRevealedCellCount() {
        return revealedHarmlessCellCountProperty.get();
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

    public RepresentableGameCell getGameCell(int row, int column) {
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

    public SimpleIntegerProperty getRevealedHarmlessCellCountProperty() {
        return revealedHarmlessCellCountProperty;
    }
}
