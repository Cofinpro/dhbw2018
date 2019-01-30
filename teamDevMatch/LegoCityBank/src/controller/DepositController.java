package controller;

import helper.OutputHelper;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.BankAccount;
import models.CustomerManager;
import persistance.UserDao;

import java.io.IOException;

public class DepositController {

    @FXML
    private TextField depositValueTextField;
    @FXML
    private TextField errorTextField;

    @FXML
    public void initialize() {
        errorTextField.setVisible(false);
        depositValueTextField.textProperty().addListener(e -> {
            validateTextInput();
        });
    }

    private void validateTextInput() {
        String input = depositValueTextField.getText();
        String validInput = input.replaceAll("[^0-9]", "");
        if (!validInput.equals(input)) {
            depositValueTextField.setText(validInput);
            errorTextField.setText("Bitte tippe nur Ziffern ein.");
            errorTextField.setVisible(true);
        } else {
            errorTextField.setText("");
            errorTextField.setVisible(false);
        }
    }

    @FXML
    void deposit(Event event) throws IOException {
        double depositValue = Double.parseDouble(depositValueTextField.getText());
        if (depositValue%5 == 0) {
            CustomerManager customerManager = CustomerManager.getInstance();
            BankAccount bankAccount = customerManager.getInspectedBankAccount();
            bankAccount.deposit(depositValue);
            goBack(event);
        } else {
            errorTextField.setText("Du kannst nur Beträge einzahlen, die durch 5 teilbar sind.");
            errorTextField.setVisible(true);
        }
    }

    @FXML
    void goBack(Event event) throws IOException {
        OutputHelper.setNextScene("bankAccountView.fxml");
    }

}
