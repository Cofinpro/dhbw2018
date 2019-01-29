package controller;

import helper.OutputHelper;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.BankAccount;
import models.CustomerManager;

import java.io.IOException;

public class DisburseController {

    @FXML private TextField disburseValueTextField;
    @FXML private TextField errorTextField;

    public void goBack(Event event) throws IOException {
        OutputHelper.setNextScene("bankAccountView.fxml");
        disburseValueTextField.textProperty().addListener(e -> validateInput());
    }

    private void validateInput() {
        String input = disburseValueTextField.getText();
        String validInput = input.replaceAll("[^0-9]", "");
        if (!validInput.equals(input)) {
            disburseValueTextField.setText(validInput);
        }
    }

    public void disburse(Event event) throws IOException {
        String input = disburseValueTextField.getText();
        double value = Double.parseDouble(input);
        if (value <= 0) {
            errorTextField.setText("Du kannst nur positive Beträge abheben.");
        } else {
            BankAccount bankAccount = CustomerManager.getInstance().getInspectedBankAccount();
            if (value > bankAccount.getBalance()) {
                errorTextField.setText("Du kannst maximal " + bankAccount.getBalance() + " abheben.");
            } else {
                bankAccount.disburse(value);
                goBack(event);
            }
        }

    }
}
