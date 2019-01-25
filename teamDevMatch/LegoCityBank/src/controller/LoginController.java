package controller;

import daos.UserDao;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.User;
import views.CustomMain;

import java.io.IOException;
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
            new CustomMain("dashboardWindow.fxml");
        } else {
            passwordField.setText("");
        }
    }
}
