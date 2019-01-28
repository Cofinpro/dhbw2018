package controller;

import persistance.UserDao;
import helper.OutputHelper;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.BankAccount;
import models.Customer;

import java.io.IOException;
import java.text.DecimalFormat;

public class BankAccountController {

    @FXML
    private TextField creationDateTextField;

    @FXML
    private TextField balanceTextField;

    @FXML private TextField accountNumberTextField;

    private UserDao userDao;
    private BankAccount bankAccount;

    @FXML
    public void initialize() {
        userDao = UserDao.getInstance();
        bankAccount = userDao.getInspectedBankAccount();
        accountNumberTextField.setText(bankAccount.getBankAccountNumber());
        creationDateTextField.setText(bankAccount.getCreationDate());
        DecimalFormat df = OutputHelper.getDecimalFormatForFigures();
        balanceTextField.setText(df.format(bankAccount.getBalance()));
    }

    @FXML
    void deleteBankAccount(Event event) throws IOException {
        Customer customer = (Customer)userDao.getLoggedInUser();
        BankAccount bankAccount = userDao.getInspectedBankAccount();
        userDao.deleteBankAccount(customer, bankAccount);
        goBack(event);
    }

    public void deposit(ActionEvent actionEvent) throws IOException {
        OutputHelper.setNextScene("depositWindow.fxml");
    }

    public void disburse(ActionEvent actionEvent) throws  IOException {
        OutputHelper.setNextScene("disburseWindow.fxml");
    }

    public void goBack(Event event) throws IOException {
            OutputHelper.setNextScene("dashboardWindow.fxml");
    }
}
