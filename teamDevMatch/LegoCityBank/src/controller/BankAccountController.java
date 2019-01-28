package controller;

import daos.UserDao;
import helper.OutputHelper;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.BankAccount;
import models.Customer;

import java.text.DecimalFormat;

public class BankAccountController {

    @FXML
    private TextField creationDateTextField;

    @FXML
    private TextField balanceTextField;

    @FXML TextField accountNumberTextField;

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
    void deleteBankAccount(Event event) {
        Customer customer = (Customer)userDao.getLoggedInUser();
        BankAccount bankAccount = userDao.getInspectedBankAccount();
        userDao.deleteBankAccount(customer, bankAccount);
    }

}
