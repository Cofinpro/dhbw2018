package application.models;

import javafx.beans.property.SimpleBooleanProperty;

import java.util.HashSet;
import java.util.Set;

public class GameCell {
    private boolean isBomb;
    private SimpleBooleanProperty isSuspectedProperty;
    private SimpleBooleanProperty isRevealedProperty;
    private Game game;
    private int row;
    private int column;
    private static final int[][] NEIGHBOUR_OFFSETS = {
            {-1, -1}, {-1, 0}, {-1, +1},
            { 0, -1},          { 0, +1},
            {+1, -1}, {+1, 0}, {+1, +1}};

    GameCell(boolean isBomb, Game game, int row, int column) {
        this.isBomb = isBomb;
        this.game = game;
        this.row = row;
        this.column = column;
        isRevealedProperty = new SimpleBooleanProperty(false);
        isSuspectedProperty = new SimpleBooleanProperty(false);
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

    boolean isSuspected() {
        return isSuspectedProperty.get();
    }

    private void revealCascade() {
        getSurroundingGameCells().stream().filter(gameCell -> !gameCell.isBomb).forEach(GameCell::tryReveal);
    }

    long getSurroundingBombCount() {
        return getSurroundingGameCells().stream().filter(gameCell -> gameCell.isBomb).count();
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

    public SimpleBooleanProperty getIsRevealedProperty() {
        return isRevealedProperty;
    }

    public void switchSuspicion() {
        isSuspectedProperty.set(!isSuspectedProperty.get());
    }

    public Game getGame() {
        return game;
    }

    boolean isRevealed() {
        return isRevealedProperty.get();
    }

    public SimpleBooleanProperty getIsSuspectedProperty() {
        return isSuspectedProperty;
    }
}
