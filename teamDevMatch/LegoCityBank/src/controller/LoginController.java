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
    @FXML private TextField passwordTextField;
    @FXML private TextField wrongPasswordTextField;
    @FXML
    public void initialize() {
        userDao = UserDao.getInstance();
        wrongPasswordTextField.setVisible(false);
        passwordTextField.textProperty().addListener(e -> wrongPasswordTextField.setVisible(false));
    }

    @FXML
    public void onLoginRequested(Event Event) throws IOException {
        String userName = usernameTextField.getText();
        try {
            Customer customer = userDao.getCustomerByUserName(userName);
            String enteredPassword = passwordTextField.getText();
            boolean isLoginSuccessful = customer.tryLogIn(enteredPassword);
            User user = userDao.getUserByUserName(userName);
            String enteredPassword = passwordTextField.getText();
            boolean isLoginSuccessful = user.tryLogIn(enteredPassword);
            if (isLoginSuccessful) {
                userDao.logUserIn(customer);
                OutputHelper.setNextScene("dashboardWindow.fxml");
            } else {
                passwordTextField.setText("");
                wrongPasswordTextField.setVisible(true);
            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }
}
