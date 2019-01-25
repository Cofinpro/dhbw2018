package controller;

import daos.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.GiroAccount;
import models.User;

public class DashboardController {

    @FXML private TextField usernameTextField;
    @FXML private TextField fullnameTextField;
    @FXML private TextField totalBalanceTextField;

    private UserDao userDao;
    private User loggedInUser;

    @FXML
    public void initialize() {
        userDao = UserDao.getInstance();
        loggedInUser = userDao.getLoggedInUser();
        usernameTextField.setText(loggedInUser.getUserName());
        fullnameTextField.setText(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
        totalBalanceTextField.setText("0.00");
    }

}
