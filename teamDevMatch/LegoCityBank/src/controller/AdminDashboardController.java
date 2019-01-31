package controller;

import helper.OutputHelper;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import models.Admin;
import models.BankAccount;
import models.Customer;
import models.CustomerManager;

import java.io.IOException;

public class AdminDashboardController {

    @FXML
    private TextField fullNameTextField;

    @FXML
    private VBox bankAccountsVBox;

    @FXML
    private TextField usernameTextField;

    @FXML
    public void initialize() {
        Admin admin = (Admin) CustomerManager.getInstance().getLoggedInUser();
        fullNameTextField.setText(admin.getFullName());
        usernameTextField.setText(admin.getUserName());
        for (BankAccount bankAccount : CustomerManager.getInstance().getAllBankAccounts()) {
            bankAccountsVBox.getChildren().add(new BankAccountSuperficialAdminViewControl(bankAccount));
        }
    }

    @FXML
    void logOut(Event event) throws IOException {
        CustomerManager.getInstance().logUserOut();
        OutputHelper.setNextScene("loginWindow.fxml");
    }

}
