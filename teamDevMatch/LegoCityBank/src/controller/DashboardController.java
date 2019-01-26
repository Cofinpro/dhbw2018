package controller;

import daos.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import models.BankAccount;
import models.Customer;
import models.GiroAccount;
import models.User;

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
        totalBalanceTextField.setText("0.00");
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
