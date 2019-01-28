package controller;

import persistance.UserDao;
import helper.OutputHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.BankAccount;
import views.Main;

import java.io.IOException;

public class BankAccountSuperficialViewControl extends AnchorPane {

    @FXML private TextField bankBalanceTextField;
    @FXML private TextField accountNumberTextField;
    @FXML private TextField accountTypeTextField;

    private BankAccount bankAccount;
    private UserDao userDao;

    @FXML
    public void initialize() {
        userDao = UserDao.getInstance();
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
            Parent root = null;
            userDao.setInspectedBankAccount(bankAccount);
            try {
                OutputHelper.setNextScene("bankAccountView.fxml");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void updateView() {
        bankBalanceTextField.setText(String.valueOf(bankAccount.getBalance()));
        accountNumberTextField.setText(bankAccount.getBankAccountNumber());
        accountTypeTextField.setText("Volksbank Girokonto");
    }


}