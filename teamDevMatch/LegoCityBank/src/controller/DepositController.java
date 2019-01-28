package controller;

import daos.UserDao;
import helper.OutputHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.BankAccount;

import java.io.IOException;

public class DepositController {

    @FXML
    private TextField depositValueTextField;

    @FXML
    void deposit(ActionEvent event) throws IOException{
        UserDao userDao = UserDao.getInstance();
        BankAccount bankAccount = userDao.getInspectedBankAccount();
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
