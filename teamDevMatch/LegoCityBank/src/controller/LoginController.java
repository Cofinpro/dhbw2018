package controller;

import models.Customer;
import models.CustomerManager;
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
    private CustomerManager customerManager;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField wrongPasswordTextField;
    @FXML
    public void initialize() {
        userDao = UserDao.getInstance();
        customerManager = CustomerManager.getInstance();
        wrongPasswordTextField.setVisible(false);
        passwordTextField.textProperty().addListener(e -> wrongPasswordTextField.setVisible(false));
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
                wrongPasswordTextField.setVisible(true);
            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }
}
