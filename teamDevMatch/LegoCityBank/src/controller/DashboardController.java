package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import models.*;
import helper.OutputHelper;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.Set;

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
        loggedInCustomer = (Customer) customerManager.getLoggedInUser();
        usernameTextField.setText(loggedInCustomer.getUserName());
        fullNameTextField.setText(loggedInCustomer.getFirstName() + " " + loggedInCustomer.getLastName());
        if (this.loggedInCustomer.getClass() == Customer.class) {
            Customer loggedInCustomer = (Customer) this.loggedInCustomer;
            DecimalFormat df = OutputHelper.getDecimalFormatForFigures();
            totalBalanceTextField.setText(df.format(loggedInCustomer.getTotalBalance()));
        }
        updateBankAccountViews();
    }

    private void updateBankAccountViews() {
        Customer loggedInCustomer = (Customer)this.loggedInCustomer;
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

    public void onDeleteAccountRequested(MouseEvent mouseEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Löschen bestätigen");
        alert.setHeaderText(loggedInCustomer + " löschen?");
        alert.setContentText("Es gibt dann kein zurück mehr.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get().equals(ButtonType.OK)) {
            deleteAccount();
            logOut(mouseEvent);
        }
    }

    private void deleteAccount() {
        customerManager.removeCustomer(loggedInCustomer);
    }
}
