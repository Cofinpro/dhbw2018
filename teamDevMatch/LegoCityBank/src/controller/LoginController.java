package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.User;

import java.util.Set;

public class LoginController {

    private Set<User> users;
    @FXML private TextField userNameTextField;

    public void onLoginRequested(ActionEvent actionEvent) {

    }
}
