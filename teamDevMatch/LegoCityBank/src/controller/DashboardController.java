package controller;

import javafx.scene.input.MouseEvent;
import models.CustomerManager;
import helper.OutputHelper;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import models.BankAccount;
import models.Customer;
import models.User;

import java.io.IOException;
import java.text.DecimalFormat;

public class DashboardController {

    @FXML private TextField usernameTextField;
    @FXML private TextField totalBalanceTextField;
    @FXML private TextField fullNameTextField;
    @FXML private VBox bankAccountsVBox;

    private CustomerManager customerManager;
    private User loggedInUser;

    @FXML
    public void initialize() {
        customerManager = CustomerManager.getInstance();
        loggedInUser = customerManager.getLoggedInUser();
        usernameTextField.setText(loggedInUser.getUserName());
        fullNameTextField.setText(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
        Customer loggedInCustomer = (Customer) this.loggedInUser;
        if (loggedInCustomer != null) {
            DecimalFormat df = OutputHelper.getDecimalFormatForFigures();
            totalBalanceTextField.setText(df.format(loggedInCustomer.getTotalBalance()));
        }
        updateBankAccountViews();
    }

    private void updateBankAccountViews() {
        Customer loggedInCustomer = (Customer)this.loggedInUser;
        if (loggedInCustomer == null) {
            return;
        }
        for (BankAccount bankAccount : loggedInCustomer.getBankAccounts()) {
            BankAccountSuperficialViewControl control = new BankAccountSuperficialViewControl(bankAccount);
            bankAccountsVBox.getChildren().addAll(control);
        }
    }

    public void logOut(MouseEvent mouseEvent) throws IOException {
        customerManager.logUserOut();
        OutputHelper.setNextScene("loginWindow.fxml");
    }

    public void addBankAccount(MouseEvent mouseEvent) throws IOException {
        OutputHelper.setNextScene("accountCreationView.fxml");
    }
}
