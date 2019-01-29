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
    public void initialize() {
        depositValueTextField.textProperty().addListener(e -> {
            validateTextInput();
        });
    }

    private void validateTextInput() {
        String input = depositValueTextField.getText();
        input = input.replace("[^1-9]", "");
        depositValueTextField.setText(input);
    }

    @FXML
    void deposit(Event event) throws IOException{
        CustomerManager customerManager = CustomerManager.getInstance();
        BankAccount bankAccount = customerManager.getInspectedBankAccount();
        double depositValue = Double.parseDouble(depositValueTextField.getText());
        bankAccount.deposit(depositValue);
        goBack(event);
    }

    @FXML
    void goBack(Event event) throws IOException {
        OutputHelper.setNextScene("bankAccountView.fxml");
    }

}
