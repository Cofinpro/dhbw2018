package controller;

import daos.UserDao;
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
            User user = userDao.getUserByUserName(userName);
            String enteredPassword = passwordField.getText();
            boolean isLoginSuccessful = user.tryLogIn(enteredPassword);
            if (isLoginSuccessful) {
                userDao.logUserIn(user);
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
