package controller;

import helper.OutputHelper;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Customer;
import models.CustomerManager;

import javax.swing.*;
import java.io.IOException;

public class RegisterController {

    @FXML private TextField fullNameTextField;
    @FXML private TextField userNameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField passwordConfirmTextField;

    public void goBack(MouseEvent mouseEvent) throws IOException {
        OutputHelper.setNextScene("loginWindow.fxml");
    }

    public void register(MouseEvent mouseEvent) throws IOException {
        String password = passwordTextField.getText();
        String passwordConfirmation = passwordConfirmTextField.getText();
        if (password.equals(passwordConfirmation)) {
            String fullName = fullNameTextField.getText();
            String userName = userNameTextField.getText();
            Customer customer = new Customer(userName, password, "Max", "Mustermann", "209u49");
            CustomerManager.getInstance().addCustomer(customer);
            goBack(mouseEvent);
        }
    }
}
