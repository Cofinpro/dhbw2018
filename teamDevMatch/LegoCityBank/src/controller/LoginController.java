package controller;

import daos.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.User;

import java.util.Set;

public class LoginController {

    private UserDao userDao;
    @FXML private TextField userNameTextField;

    @FXML
    public void initialize() {
        userDao = UserDao.getInstance();
    }


    public void onLoginRequested(ActionEvent actionEvent) {
        User user = UserDao.getUserByUserName(userNameTextField.getText());
    }
}
