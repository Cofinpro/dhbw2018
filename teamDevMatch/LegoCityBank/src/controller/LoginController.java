package controller;

import daos.UserDao;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.User;

import java.util.Set;

public class LoginController {

    private UserDao userDao;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordField;

    @FXML
    public void initialize() {
        userDao = UserDao.getInstance();
    }

    @FXML
    public void onLoginRequested(Event Event) {
        User user = userDao.getUserByUserName(usernameTextField.getText());
        String enteredPassword = passwordField.getText();
        boolean isLoginSuccessful = user.tryLogIn(enteredPassword);
        if (isLoginSuccessful) {

        } else {

        }
    }
}
