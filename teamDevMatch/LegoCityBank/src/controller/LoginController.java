package controller;

import javafx.event.ActionEvent;
import models.Customer;
import models.CustomerManager;
import persistance.UserDao;
import exceptions.UserNotFoundException;
import helper.OutputHelper;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    private static final String WRONG_PASSWORD_MESSAGE = "falsches Passwort";
    private static final String USER_NOT_FOUND_MESSAGE = "dieser Benutzer existiert nicht";

    private UserDao userDao;
    private CustomerManager customerManager;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField errorTextField;

    @FXML
    public void initialize() {
        userDao = UserDao.getInstance();
        customerManager = CustomerManager.getInstance();
        errorTextField.setVisible(false);
        passwordTextField.textProperty().addListener(e -> errorTextField.setVisible(false));
        usernameTextField.textProperty().addListener(e -> errorTextField.setVisible(false));
    }

    @FXML
    public void onLoginRequested(Event Event) throws IOException {
        String userName = usernameTextField.getText();
        try {
            Customer customer = customerManager.getCustomerByUserName(userName);
            String enteredPassword = passwordTextField.getText();
            boolean isLoginSuccessful = customer.tryLogIn(enteredPassword);
            if (isLoginSuccessful) {
                customerManager.logUserIn(customer);
                OutputHelper.setNextScene("dashboardWindow.fxml");
            } else {
                passwordTextField.setText("");
                errorTextField.setText(WRONG_PASSWORD_MESSAGE);
                errorTextField.setVisible(true);
            }
        } catch (UserNotFoundException e) {
            errorTextField.setText(USER_NOT_FOUND_MESSAGE);
            errorTextField.setVisible(true);
        }
    }

    public void register(ActionEvent actionEvent) throws IOException {
        OutputHelper.setNextScene("registerWindow.fxml");
    }
}
