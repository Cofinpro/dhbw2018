package controller;

import javafx.scene.input.MouseEvent;
import models.CustomerManager;
import helper.OutputHelper;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import models.BankAccount;
import models.Customer;

import java.io.IOException;
import java.text.DecimalFormat;

public class DashboardController {

    @FXML private TextField usernameTextField;
    @FXML private TextField totalBalanceTextField;
    @FXML private TextField fullNameTextField;
    @FXML private VBox bankAccountsVBox;

    private CustomerManager customerManager;
    private Customer loggedInCustomer;

    @FXML
    public void initialize() {
        customerManager = CustomerManager.getInstance();
        loggedInCustomer = customerManager.getLoggedInCustomer();
        usernameTextField.setText(loggedInCustomer.getUserName());
        fullNameTextField.setText(loggedInCustomer.getUserName());
        Customer loggedInCustomer = (Customer) this.loggedInCustomer;
        if (loggedInCustomer != null) {
            DecimalFormat df = OutputHelper.getDecimalFormatForFigures();
            totalBalanceTextField.setText(df.format(loggedInCustomer.getTotalBalance()));
        }
        updateBankAccountViews();
    }

    private void updateBankAccountViews() {
        Customer loggedInCustomer = (Customer) this.loggedInCustomer;
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
