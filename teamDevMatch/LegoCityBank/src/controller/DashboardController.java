package controller;

import javafx.scene.input.MouseEvent;
import models.*;
import helper.OutputHelper;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Set;

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
        if (this.loggedInUser.getClass() == Customer.class) {
            Customer loggedInCustomer = (Customer) this.loggedInUser;
            DecimalFormat df = OutputHelper.getDecimalFormatForFigures();
            totalBalanceTextField.setText(df.format(loggedInCustomer.getTotalBalance()));
        }
        updateBankAccountViews();
    }

    private void updateBankAccountViews() {
        if (loggedInUser.getClass() == Admin.class) {
            Set<BankAccount> bankAccounts =  CustomerManager.getInstance().getAllBankAccounts();
            for (BankAccount bankAccount : bankAccounts) {
                BankAccountSuperficialAdminViewControl control = new BankAccountSuperficialAdminViewControl(bankAccount);
                bankAccountsVBox.getChildren().add(control);
            }
        }
        else if (loggedInUser.getClass() == Customer.class){
            Customer loggedInCustomer = (Customer)this.loggedInUser;
            for (BankAccount bankAccount : loggedInCustomer.getBankAccounts()) {
                BankAccountSuperficialViewControl control = new BankAccountSuperficialViewControl(bankAccount);
                bankAccountsVBox.getChildren().addAll(control);
            }
        } else {
            throw new RuntimeException();
        }
    }

    public void logOut(MouseEvent mouseEvent) throws IOException {
        customerManager.logUserOut();
        OutputHelper.setNextScene("loginWindow.fxml");
    }

    public void addBankAccount(MouseEvent mouseEvent) throws IOException {
        OutputHelper.setNextScene("accountCreationView.fxml");
    }

    public void onDeleteAccountRequested(MouseEvent mouseEvent) {
        deleteAccount();
    }

    private void deleteAccount() {
        customerManager.removeCustomer((Customer) loggedInUser);
    }
}
