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

    public void goBack(Event event) throws IOException {
        OutputHelper.setNextScene("bankAccountView.fxml");
    }

    public void disburse(Event event) throws IOException {
        String input = disburseValueTextField.getText();
        try {
            double value = Double.parseDouble(input);
            BankAccount bankAccount = CustomerManager.getInstance().getInspectedBankAccount();
            bankAccount.disburse(value);
            goBack(event);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
