package controller;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import daos.UserDao;
import exceptions.UserNotFoundException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.User;
import views.CustomMain;
import views.Main;

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
    public void onLoginRequested(Event Event) throws IOException {

        String userName = usernameTextField.getText();
        try {
            User user = userDao.getUserByUserName(userName);
            String enteredPassword = passwordField.getText();
            boolean isLoginSuccessful = user.tryLogIn(enteredPassword);
            if (isLoginSuccessful) {
                userDao.logUserIn(user);
                Parent root = FXMLLoader.load(new CustomMain().getClass().getResource("dashboardWindow.fxml"));
                Main.PRIMARY_STAGE.setScene(new Scene(root, 1000, 1000));
            } else {
                passwordField.setText("");
            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }
}
