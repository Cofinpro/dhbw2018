package controls;

import javafx.scene.control.ChoiceBox;
import models.ChemicalElement;

public class PeriodPicker extends ChoiceBox<String> {

    private static final String CHOICE_PREFIX = "period ";

    public PeriodPicker() {
        createChoicesAndSetDefault();
    }

    private void createChoicesAndSetDefault() {
        for (int i = 1; i <= ChemicalElement.MAX_PERIOD; i++) {
            this.getItems().add(CHOICE_PREFIX + i);
        }
        this.setValue(CHOICE_PREFIX + 1);
    }

    public int getChosenPeriod() {
        String choice = this.getValue();
        int period = Integer.parseInt(choice.substring(CHOICE_PREFIX.length()));
        return period;
    }
}
