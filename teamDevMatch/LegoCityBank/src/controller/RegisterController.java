package controller;

import helper.OutputHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Customer;
import models.CustomerManager;
import models.User;

import java.io.IOException;

public class RegisterController {


    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField userNameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField passwordConfirmTextField;

    @FXML private TextArea wrongPasswordTextArea;
    @FXML private TextArea wrongUsernameTextArea;
    @FXML private TextArea wrongNameTextArea;

    @FXML
    public void initialize() {
        setupInputValidation();
    }

    private void setupInputValidation() {
        firstNameTextField.textProperty().addListener(e -> validateName());
        lastNameTextField.textProperty().addListener(e -> validateName());
    }

    private void validateName() {
        String errorMessage = User.isfirstNameValid(firstNameTextField.getText());
        if (errorMessage.equals("")) {
            errorMessage = User.isLastNameValid(lastNameTextField.getText());
        }
        if (errorMessage.equals("")) {
            wrongNameTextArea.setVisible(false);
        } else {
            wrongNameTextArea.setText(errorMessage);
            wrongNameTextArea.setVisible(true);
        }
    }

    public void goBack(MouseEvent mouseEvent) throws IOException {
        OutputHelper.setNextScene("loginWindow.fxml");
    }

    public void register(MouseEvent mouseEvent) throws IOException {
        String password = passwordTextField.getText();
        String passwordConfirmation = passwordConfirmTextField.getText();
        if (password.equals(passwordConfirmation)) {
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String userName = userNameTextField.getText();
            Customer customer = new Customer(userName, password, firstName, lastName);
            if(CustomerManager.getInstance().addCustomer(customer)) {
                goBack(mouseEvent);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Benutzername vergeben");
                alert.setHeaderText("Ändere den Benutzernamen!");
                alert.setContentText("Der Benutzername " + userName + " ist schon vergeben.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Passwörter stimmen nicht überein");
            alert.setHeaderText("Tippe die Passwörter erneut ein!");
            alert.setContentText("Die Passwörter müssen übereinstimmen.");
            alert.showAndWait();
        }
    }
}
