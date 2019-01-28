package controller;

import persistance.UserDao;
import helper.OutputHelper;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import models.BankAccount;
import models.Customer;
import models.User;

import java.text.DecimalFormat;

public class DashboardController {

    @FXML private TextField usernameTextField;
    @FXML private TextField fullnameTextField;
    @FXML private TextField totalBalanceTextField;
    @FXML private VBox bankAccountsVBox;

    private UserDao userDao;
    private User loggedInUser;

    @FXML
    public void initialize() {
        userDao = UserDao.getInstance();
        loggedInUser = userDao.getLoggedInUser();
        usernameTextField.setText(loggedInUser.getUserName());
        fullnameTextField.setText(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
        Customer loggedInCustomer = (Customer) loggedInUser;
        if (loggedInCustomer != null) {
            DecimalFormat df = OutputHelper.getDecimalFormatForFigures();
            totalBalanceTextField.setText(df.format(loggedInCustomer.getTotalBalance()));
        }
        updateBankAccountViews();
    }

    private void updateBankAccountViews() {
        Customer loggedInCustomer = (Customer) loggedInUser;
        if (loggedInCustomer == null) {
            return;
        }
        for (BankAccount bankAccount : loggedInCustomer.getBankAccounts()) {
            BankAccountSuperficialViewControl control = new BankAccountSuperficialViewControl(bankAccount);
            bankAccountsVBox.getChildren().addAll(control);
        }
    }

}
