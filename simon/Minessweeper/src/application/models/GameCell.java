package application.models;

import application.enums.GameState;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.HashSet;
import java.util.Set;

public class GameCell {
    private boolean isBomb;
    private SimpleBooleanProperty isSuspectedValue;
    private SimpleBooleanProperty isRevealedProperty;
    private StringBinding representation;
    private Game game;
    private int row;
    private int column;
    private static final int[][] NEIGHBOUR_OFFSETS = {
            {-1, -1}, {-1, 0}, {-1, +1},
            { 0, -1},          { 0, +1},
            {+1, -1}, {+1, 0}, {+1, +1}};

    public GameCell(boolean isBomb, Game game, int row, int column) {
        this.isBomb = isBomb;
        this.game = game;
        this.row = row;
        this.column = column;
        isRevealedProperty = new SimpleBooleanProperty(false);
        isSuspectedValue = new SimpleBooleanProperty(false);
        this.representation = new StringBinding() {
            {
                super.bind(isRevealedProperty, isSuspectedValue, game.getGameStateProperty());
            }
            @Override
            protected String computeValue() {
                if (game.getGameState() == GameState.LOST) {
                    if (isBomb) {
                        if (isRevealed()) {
                            return "\uD83D\uDCA5";
                        }
                        return "\uD83D\uDCA3";
                    } else if (isSuspected()) {
                        return "\uD83D\uDEAB";
                    }
                }
                if (isRevealedProperty.get()) {
                    if (isBomb) {
                        return "\uD83D\uDCA3";
                    } else {
                        long count = getSurroundingBombCount();
                        if (count == 0) {
                            return " ";
                        }
                        return "" + getSurroundingBombCount();
                    }
                } else {
                    if (isSuspectedValue.get()) {
                        return "\uD83C\uDFC1";
                    }
                    return " ";
                }
            }
        };
    }

    public void tryReveal() {
        if (isRevealed() || isSuspected()) {
            return;
        }
        isRevealedProperty.set(true);
        if (isBomb) {
            game.loseGame();
            return;
        }
        if (getSurroundingBombCount() == 0) {
            revealCascade();
        }
    }

    private boolean isSuspected() {
        return isSuspectedValue.get();
    }

    private void revealCascade() {
        getSurroundingGameCells().stream().filter(gameCell -> !gameCell.isBomb).forEach(GameCell::tryReveal);
    }

    private long getSurroundingBombCount() {
        return getSurroundingGameCells().stream().filter(gameCell -> gameCell.isBomb).count();
    }

    public StringBinding representationProperty() {
        return representation;
    }

    private Set<GameCell> getSurroundingGameCells() {
        HashSet<GameCell> result = new HashSet<>();
        int rowCount = game.getRowCount();
        int columnCount = game.getColumnCount();
        for (int[] offset : NEIGHBOUR_OFFSETS) {
            int neighborRow = row + offset[0];
            int neighborColumn = column + offset[1];
            if (neighborRow >= 0 && neighborRow < rowCount && neighborColumn >= 0 && neighborColumn < columnCount) {
                result.add(game.getGameCell(neighborRow, neighborColumn));
            }
        }
        return result;
    }

    public SimpleBooleanProperty isRevealedProperty() {
        return isRevealedProperty;
    }

    public void switchSuspicion() {
        isSuspectedValue.set(!isSuspectedValue.get());
    }

    public Game getGame() {
        return game;
    }

    private boolean isRevealed() {
        return isRevealedProperty.get();
    }
}
