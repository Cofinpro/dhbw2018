package application.models;

import application.enums.GameState;
import javafx.beans.binding.StringBinding;

public class RepresentableGameCell extends GameCell {
    private StringBinding representation;

    public RepresentableGameCell(boolean isBomb, Game game, int row, int column) {
        super(isBomb, game, row, column);
        this.representation = new StringBinding() {
            {
                super.bind(getIsRevealedProperty(), getIsSuspectedProperty(), game.getGameStateProperty());
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
                if (isRevealed()) {
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
                    if (isSuspected()) {
                        return "\uD83C\uDFC1";
                    }
                    return " ";
                }
            }
        };
    }

    public StringBinding representationProperty() {
        return representation;
    }
}
