package controller;

import helper.OutputHelper;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import models.*;

import java.io.IOException;

public class AccountCreationController {

    private Customer customer;

    @FXML
    public void initialize() {
        customer = (Customer)CustomerManager.getInstance().getLoggedInUser();
    }

    @FXML
    void createGiroAccount(ActionEvent event) throws IOException {
        customer.addBankAccount(new GiroAccount(customer));
        goBack(event);
    }

    @FXML
    void createBankBookAccount(ActionEvent event) throws IOException {
        customer.addBankAccount(new BankBook(customer));
        goBack(event);
    }

    @FXML
    void createPremiumAccount(ActionEvent event) throws IOException {
        customer.addBankAccount(new PremiumAccount(customer));
        goBack(event);
    }

    @FXML
    void createMetalAccount(ActionEvent event) throws IOException {
        customer.addBankAccount(new MetalAccount(customer));
        goBack(event);
    }

    public void goBack(Event event) throws IOException {
        OutputHelper.setNextScene("dashboardWindow.fxml");
    }
}

