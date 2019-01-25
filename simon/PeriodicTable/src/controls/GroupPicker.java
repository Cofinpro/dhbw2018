package controls;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.ChoiceBox;
import models.ChemicalElement;

public class GroupPicker extends ChoiceBox<String> {

    private static final String CHOICE_PREFIX = "group ";

    public GroupPicker() {
        createChoicesAndSetDefault();
    }

    private void createChoicesAndSetDefault() {
        for (int i = 1; i <= ChemicalElement.MAX_GROUP; i++) {
            this.getItems().add(CHOICE_PREFIX + i);
        }
        this.setValue(CHOICE_PREFIX + 1);
    }

    public int getChosenGroup() {
        String choice = this.getValue();
        int group = Integer.parseInt(choice.substring(CHOICE_PREFIX.length()));
        return group;
    }
}
