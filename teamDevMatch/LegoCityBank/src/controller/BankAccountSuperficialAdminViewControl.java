package controller;

import helper.OutputHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import models.BankAccount;
import models.CustomerManager;
import views.Main;

import java.io.IOException;

public class BankAccountSuperficialAdminViewControl extends GridPane {

    @FXML
    private TextField accountNumberTextField;

    @FXML
    private TextField bankBalanceTextField;

    @FXML
    private TextField fullNameTextField;

    @FXML
    private TextField accountTypeTextField;

    private CustomerManager customerManager;
    private BankAccount bankAccount;

    public BankAccountSuperficialAdminViewControl(BankAccount bankAccount) {
        customerManager = CustomerManager.getInstance();
        this.bankAccount = bankAccount;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("bankAccountSuperficialView.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateView();

        setOnMouseClicked(e -> {
            Parent root = null;
            customerManager.setInspectedBankAccount(bankAccount);
            try {
                OutputHelper.setNextScene("bankAccountView.fxml");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void updateView() {
        accountNumberTextField.setText(bankAccount.getBankAccountNumber());
        bankBalanceTextField.setText(String.valueOf(bankAccount.getBalance()));
        fullNameTextField.setText(bankAccount.getOwner().getFirstName() + " " + bankAccount.getOwner().getLastName());
        accountTypeTextField.setText(bankAccount.getAccountType());
    }


}
