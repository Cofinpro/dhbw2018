package controller;

import helper.OutputHelper;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import models.BankAccount;
import models.Customer;
import models.CustomerManager;

import java.io.IOException;
import java.text.DecimalFormat;

public class BankAccountAdminController {

    @FXML
    private TextField creationDateTextField;

    @FXML
    private TextField balanceTextField;
    @FXML
    private TextField accountNumberTextField;

    @FXML
    public void initialize() {
        BankAccount bankAccount = CustomerManager.getInstance().getInspectedBankAccount();
        creationDateTextField.setText(bankAccount.getCreationDate());
        DecimalFormat df = OutputHelper.getDecimalFormatForFigures();
        balanceTextField.setText(df.format(bankAccount.getBalance()));
        accountNumberTextField.setText(bankAccount.getBankAccountNumber());
    }

    @FXML
    void goBack(Event event) throws IOException {
        OutputHelper.setNextScene("dashboardWindow.fxml");
    }

    @FXML
    void onRequstDeleteBankAccount(Event event) throws IOException {
        CustomerManager.getInstance().removeBankAccount();
        goBack(event);
    }
}
