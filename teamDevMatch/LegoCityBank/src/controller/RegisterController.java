package controller;

import helper.OutputHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Customer;
import models.CustomerManager;

import javax.swing.*;
import java.io.IOException;

public class RegisterController {

    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField userNameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField passwordConfirmTextField;

    public void goBack(MouseEvent mouseEvent) throws IOException {
        OutputHelper.setNextScene("loginWindow.fxml");
        passwordConfirmTextField.textProperty().addListener(e -> {
            if (passwordConfirmTextField.getText().equals(passwordTextField.getText())) {
                //password should be red
            } else {
                //password should be normal
            }
        });
    }

    public void register(MouseEvent mouseEvent) throws IOException {
        String password = passwordTextField.getText();
        String passwordConfirmation = passwordConfirmTextField.getText();
        if (password.equals(passwordConfirmation)) {
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String userName = userNameTextField.getText();
            Customer customer = new Customer(userName, password, firstName, lastName);
            if(CustomerManager.getInstance().addCustomer(customer)) {
                goBack(mouseEvent);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Benutzername vergeben");
                alert.setHeaderText("Ändere den Benutzernamen!");
                alert.setContentText("Der Benutzername " + userName + " ist schon vergeben.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Passwörter stimmen nicht überein");
            alert.setHeaderText("Tippe die Passwörter erneut ein!");
            alert.setContentText("Die Passwörter müssen übereinstimmen.");
            alert.showAndWait();
        }
    }
}
