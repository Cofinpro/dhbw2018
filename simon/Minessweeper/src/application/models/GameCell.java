package application.models;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleBooleanProperty;

public class GameCell {
    private SimpleBooleanProperty isRevealed;
    private StringBinding representation;

    GameCell() {
        isRevealed = new SimpleBooleanProperty(false);
        this.representation = new StringBinding() {
            {
                super.bind(isRevealed);
            }
            @Override
            protected String computeValue() {
                if (isRevealed.get()) {
                    return  " ";
                } else {
                    return "x";
                }
            }
        };
    }

    public void reveal() {
        isRevealed.set(true);
    }

    public StringBinding representationProperty() {
        return representation;
    }
}
