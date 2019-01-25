package gui;

import controls.GroupPicker;
import controls.PeriodPicker;
import exceptions.ChemicalElementNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import models.ChemicalElement;
import models.PeriodicSystem;

import java.util.Observable;

public class ChemicalElementController {

    private ChemicalElement currentChemicalElement;
    private PeriodicSystem periodicSystem;
    @FXML private PeriodPicker periodPicker;
    @FXML private GroupPicker groupPicker;
    @FXML private TextArea outputTextView;

    @FXML
    public void initialize() {
        periodicSystem = PeriodicSystem.getInstance();
        currentChemicalElement = periodicSystem.getFirstChemicalElement();
        outputTextView.setText(currentChemicalElement.toString());
        periodPicker.valueProperty().addListener(e -> updateCurrentChemicalElementAndDisplayIt());
        groupPicker.valueProperty().addListener(e -> updateCurrentChemicalElementAndDisplayIt());
    }

    public void updateCurrentChemicalElementAndDisplayIt() {
        int group = groupPicker.getChosenGroup();
        int period = periodPicker.getChosenPeriod();
        try {
            currentChemicalElement = periodicSystem.getChemicalElement(period, group);
            displayCurrentChemicalElement();
        } catch (ChemicalElementNotFoundException e) {
            outputTextView.setText(e.getMessage());
        }
    }

    private void displayCurrentChemicalElement() {
        outputTextView.setText(currentChemicalElement.toString());
    }


}
