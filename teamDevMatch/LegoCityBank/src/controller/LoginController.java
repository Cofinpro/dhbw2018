package controller;

import models.Customer;
import persistance.UserDao;
import exceptions.UserNotFoundException;
import helper.OutputHelper;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.User;

import java.io.IOException;

public class LoginController {

    private UserDao userDao;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordField;
    @FXML private TextField wrongPasswordTextField;
    @FXML
    public void initialize() {
        userDao = UserDao.getInstance();
        wrongPasswordTextField.setVisible(false);
        passwordField.textProperty().addListener(e -> wrongPasswordTextField.setVisible(false));
    }

    @FXML
    public void onLoginRequested(Event Event) throws IOException {
        String userName = usernameTextField.getText();
        try {
            Customer customer = userDao.getCustomerByUserName(userName);
            String enteredPassword = passwordField.getText();
            boolean isLoginSuccessful = customer.tryLogIn(enteredPassword);
            if (isLoginSuccessful) {
                userDao.logUserIn(customer);
                OutputHelper.setNextScene("dashboardWindow.fxml");
            } else {
                passwordField.setText("");
                wrongPasswordTextField.setVisible(true);
            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }
}
