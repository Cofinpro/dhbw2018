package controller;

import models.CustomerManager;
import models.MetalAccount;
import persistance.UserDao;
import helper.OutputHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.BankAccount;
import persistance.UserDao;
import views.Main;

import java.io.IOException;

public class BankAccountSuperficialViewControl extends GridPane {

    @FXML
    private TextField bankBalanceTextField;
    @FXML private TextField accountNumberTextField;
    @FXML private TextField accountTypeTextField;

    private BankAccount bankAccount;
    private CustomerManager customerManager;

    @FXML
    public void initialize() {
        customerManager = CustomerManager.getInstance();
    }

    public BankAccountSuperficialViewControl(BankAccount bankAccount) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("bankAccountSuperficialView.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.bankAccount = bankAccount;
        updateView();

        setOnMouseClicked(e -> {
            customerManager.setInspectedBankAccount(bankAccount);
            try {
                OutputHelper.setNextScene("bankAccountView.fxml");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void updateView() {
        bankBalanceTextField.setText(OutputHelper.getDecimalFormatForFigures().format(bankAccount.getBalance()));
        accountNumberTextField.setText(bankAccount.getBankAccountNumber());
        accountTypeTextField.setText(bankAccount.getAccountType());
    }


}