package controller;

import helper.OutputHelper;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import models.BankAccount;
import models.CustomerManager;
import persistance.UserDao;

import java.io.IOException;

public class DepositController {

    public Button depositButton;
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
        depositValueTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                deposit(e);
            }
        });
        depositButton.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                deposit(e);
            }
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
    void deposit(Event event) {
        try {
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
        } catch (NumberFormatException e) {
            errorTextField.setText("Bitte Summe eingeben.");
            errorTextField.setVisible(true);
        }
    }

    @FXML
    void goBack(Event event) {
        try {
            OutputHelper.setNextScene("bankAccountView.fxml");
        } catch (IOException e) {
            errorTextField.setText("Es gibt einen Fehler im Programm:\n" + e.getMessage());
            errorTextField.setVisible(true);
        }
    }

}
