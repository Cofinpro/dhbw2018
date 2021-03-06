package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import models.CustomerManager;
import helper.OutputHelper;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.BankAccount;
import models.Customer;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Optional;

public class BankAccountController {

    @FXML
    private TextField creationDateTextField;

    @FXML
    private TextField balanceTextField;

    @FXML private TextField accountNumberTextField;

    private CustomerManager customerManager;
    private BankAccount bankAccount;

    @FXML
    public void initialize() {
        customerManager = CustomerManager.getInstance();
        bankAccount = customerManager.getInspectedBankAccount();
        accountNumberTextField.setText(bankAccount.getBankAccountNumber().toString());
        creationDateTextField.setText(bankAccount.getCreationDate());
        DecimalFormat df = OutputHelper.getDecimalFormatForFigures();
        balanceTextField.setText(df.format(bankAccount.getBalance()));
    }

    @FXML
    void onRequstDeleteBankAccount(Event event) throws IOException {
        Customer loggedInUser = (Customer)customerManager.getLoggedInUser();
        BankAccount bankAccount = customerManager.getInspectedBankAccount();
        if (bankAccount.isDeletable()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(bankAccount + " löschen");
            alert.setHeaderText(bankAccount + " löschen?");
            alert.setContentText("Es gibt dann kein zurück mehr.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get().equals(ButtonType.OK)) {
                loggedInUser.deleteBankAccount(bankAccount);
                goBack(event);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(bankAccount + " nicht löschbar");
            alert.setHeaderText(bankAccount + " kann nicht gelöscht werden");
            alert.setContentText("Bank Accounts können erst gelöscht werden, wenn ihr Kontostand 0 ist. Sie müssen dafür noch Geld ein- oder auszahlen.");
            alert.showAndWait();
        }
    }

    public void deposit(ActionEvent actionEvent) throws IOException {
        if (CustomerManager.getInstance().getLoggedInUser().getClass() != Customer.class) {
            throw new RuntimeException("This method should only be called with a customer logged in");
        }
        OutputHelper.setNextScene("depositWindow.fxml");
    }

    public void disburse(ActionEvent actionEvent) throws  IOException {
        if (CustomerManager.getInstance().getLoggedInUser().getClass() != Customer.class) {
            throw new RuntimeException("This method should only be called with a customer logged in");
        }
        if (bankAccount.getBalance() > 0) {
            OutputHelper.setNextScene("disburseWindow.fxml");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kontostand zu niedrig");
            alert.setContentText("Der Kontostand ist zu niedrig um Geld von ihm abheben zu können.");
            alert.showAndWait();
        }
    }

    public void goBack(Event event) throws IOException {
            OutputHelper.setNextScene("dashboardWindow.fxml");
    }
}
