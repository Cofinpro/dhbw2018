package controller;

import helper.OutputHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    }

    @FXML
    void deposit(ActionEvent event) throws IOException{
        CustomerManager customerManager = CustomerManager.getInstance();
        BankAccount bankAccount = customerManager.getInspectedBankAccount();
        try {
            double depositValue = Double.parseDouble(depositValueTextField.getText());
            bankAccount.deposit(depositValue);
            goBack(event);
        } catch (NumberFormatException e) {

        }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        OutputHelper.setNextScene("bankAccountView.fxml");
    }

}
