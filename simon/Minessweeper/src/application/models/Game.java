package application.models;

import application.enums.Difficulty;
import application.enums.GameState;
import application.helper.RandomHelper;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Observable;

public class Game extends Observable {
    private static Game ourInstance = new Game();
    private RepresentableGameCell[][] gameCells;
    private SimpleObjectProperty<GameState> gameStateProperty;
    private SimpleIntegerProperty revealedHarmlessCellCountProperty;
    private SimpleIntegerProperty suspectedCellCountProperty;
    private Long startingTimeMillis;
    private Long finishingTimeMillis;
    private IntegerBinding suspectedRemainingBombCountBinding;

    public static Game getInstance() {
        return ourInstance;
    }

    private Game() {
        gameCells = new RepresentableGameCell[0][0];
        gameStateProperty = new SimpleObjectProperty<>(GameState.PLAYING);
        revealedHarmlessCellCountProperty = new SimpleIntegerProperty(0);
        revealedHarmlessCellCountProperty.addListener(event -> {
            if (getGameState() != GameState.LOST && getRevealedCellCount() == getCellCount() - Settings.getInstance().getDifficulty().getBombCountOverall()) {
                setGameState(GameState.WON);
                finishingTimeMillis = System.currentTimeMillis();
            }
            if (startingTimeMillis == null) {
                startingTimeMillis = System.currentTimeMillis();
            }
        });
        suspectedCellCountProperty = new SimpleIntegerProperty(0);
        Settings.getInstance().getDifficultyProperty().addListener((observable, oldValue, newValue) -> setup());
        suspectedRemainingBombCountBinding = new IntegerBinding() {
            {
                bind(suspectedCellCountProperty, Settings.getInstance().getDifficultyProperty());
            }
            @Override
            protected int computeValue() {
                Difficulty difficulty = Settings.getInstance().getDifficulty();
                if (difficulty == null) {
                    return 0;
                }
                int suspectedRemainingBombCount = difficulty.getBombCountOverall() - Game.getInstance().getSuspectedCellCount();
                if (suspectedRemainingBombCount < 0) {
                    return 0;
                }
                return suspectedRemainingBombCount;
            }
        };
    }

    public void resetTimers() {
        startingTimeMillis = null;
        finishingTimeMillis = null;
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
        if (Settings.getInstance().getDifficulty() == null) {
            return;
        }
        gameCells = RandomHelper.getGameField(this);
        gameStateProperty.set(GameState.PLAYING);
        revealedHarmlessCellCountProperty.set(0);
        suspectedCellCountProperty.set(0);
        startingTimeMillis = null;
        finishingTimeMillis = null;
        setChanged();
        notifyObservers();
    }

    public int getRevealedCellCount() {
        return revealedHarmlessCellCountProperty.get();
    }

    public int getRowCount() {
        return gameCells.length;
    }

    public int getColumnCount() {
        if (getRowCount() == 0) {
            return 0; //when there are no rows, there cannot be any columns
        }
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

    public int getSuspectedCellCount() {
        return suspectedCellCountProperty.get();
    }

    public SimpleIntegerProperty getSuspectedCellCountProperty() {
        return suspectedCellCountProperty;
    }

    public void incrementSuspectedCellCount(int incrementation) {
        suspectedCellCountProperty.set(getSuspectedCellCount() + incrementation);
    }

    public IntegerBinding getSuspectedRemainingBombCountBinding() {
        return suspectedRemainingBombCountBinding;
    }
}
