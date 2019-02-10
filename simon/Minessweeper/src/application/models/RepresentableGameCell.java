package application.models;

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
                if (isRevealed()) {
                    return getRepresentationForRevealed();
                } else {
                    return getRepresentationForUnrevealed();
                }
            }

            private String getRepresentationForUnrevealed() {
                switch (game.getGameState()) {
                    case PLAYING:
                        return getRepresentationForUnrevealedWhenGameIsGoingOn();
                    case WON:
                    case LOST:
                        return getRepresentationForUnrevealedWhenGameIsFinished();
                    default:
                        throw new RuntimeException("unexpected case");
                }
            }

            private String getRepresentationForUnrevealedWhenGameIsGoingOn() {
                if (isSuspected()) {
                    return getRepresentationForSuspected();
                } else {
                    return " ";
                }
            }

            private String getRepresentationForSuspected() {
                return "\uD83C\uDFC1";
            }

            private String getRepresentationForUnrevealedWhenGameIsFinished() {
                if (isBomb) {
                    return getRepresentationForUnrevealedBombWhenGameIsFinished();
                } else if (isSuspected()) {
                    return getRepresentationForUnrevealedSuspectedButHarmlessFieldWhenGameIsFinished();
                } else {
                    return " ";
                }
            }

            private String getRepresentationForUnrevealedBombWhenGameIsFinished() {
                return "\uD83D\uDCA3";
            }

            private String getRepresentationForRevealedBomb() {
                return "\uD83D\uDCA5";
            }

            private String getRepresentationForUnrevealedSuspectedButHarmlessFieldWhenGameIsFinished() {
                return "\uD83D\uDEAB";
            }

            private String getRepresentationForRevealed() {
                if (isBomb) {
                    return getRepresentationForRevealedBomb();
                } else {
                    return getRepresentationForRevealedHarmlessField();
                }
            }

            private String getRepresentationForRevealedHarmlessField() {
                long count = getSurroundingBombCount();
                if (count == 0) {
                    return " ";
                }
                return "" + getSurroundingBombCount();
            }
        };
    }

    public StringBinding representationProperty() {
        return representation;
    }
}
