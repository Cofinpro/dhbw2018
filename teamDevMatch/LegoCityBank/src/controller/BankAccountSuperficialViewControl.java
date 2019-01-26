package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.BankAccount;
import views.CustomMain;

import java.io.IOException;

public class BankAccountSuperficialViewControl extends AnchorPane {

    @FXML private TextField bankBalanceTextField;
    @FXML private TextField accountNumberTextField;
    @FXML private TextField accountTypeTextField;

    private BankAccount bankAccount;

    public BankAccountSuperficialViewControl(BankAccount bankAccount) {
        FXMLLoader loader = new FXMLLoader(new CustomMain().getClass().getResource("bankAccountSuperficialView.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.bankAccount = bankAccount;
        updateView();
    }

    private void updateView() {
        bankBalanceTextField.setText(String.valueOf(bankAccount.getBalance()));
        accountNumberTextField.setText(bankAccount.getBankAccountNumber());
        accountTypeTextField.setText("Volksbank Girokonto");
    }


}