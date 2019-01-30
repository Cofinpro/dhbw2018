package controller;

import helper.OutputHelper;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import models.*;

import java.io.IOException;

public class AccountCreationController {

    private Customer customer;

    @FXML
    public void initialize() {
        customer = CustomerManager.getInstance().getLoggedInCustomer();
    }

    @FXML
    void createGiroAccount(ActionEvent event) throws IOException {
        customer.addBankAccount(new GiroAccount());
        goBack(event);
    }

    @FXML
    void createBankBookAccount(ActionEvent event) throws IOException {
        customer.addBankAccount(new BankBook());
        goBack(event);
    }

    @FXML
    void createPremiumAccount(ActionEvent event) throws IOException {
        customer.addBankAccount(new PremiumAccount());
        goBack(event);
    }

    @FXML
    void createMetalAccount(ActionEvent event) throws IOException {
        customer.addBankAccount(new MetalAccount());
        goBack(event);
    }

    public void goBack(Event event) throws IOException {
        OutputHelper.setNextScene("dashboardWindow.fxml");
    }
}

