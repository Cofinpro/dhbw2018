package controller;

import helper.OutputHelper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import models.BankAccount;
import models.CustomerManager;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DisburseController {

    public Button disburseButton;
    @FXML private TextField disburseValueTextField;
    @FXML private TextField errorTextField;

    @FXML
    public void initialize() {
        errorTextField.setVisible(false);
        disburseValueTextField.textProperty().addListener(e -> validateInput());
        disburseValueTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                disburse(event);
            }
        });
        disburseButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                disburse(event);
            }
        });
    }

    public void goBack(Event event) {
        try {
            OutputHelper.setNextScene("bankAccountView.fxml");
        } catch (IOException e) {
            errorTextField.setText("Es gibt einen schlimmen Fehler:\n" + e.getMessage());
            errorTextField.setVisible(true);
        }

    }

    private void validateInput() {
        String input = disburseValueTextField.getText();
        String validInput = input.replaceAll("[^0-9|.|,]", "");
        if (!validInput.equals(input)) {
            disburseValueTextField.setText(validInput);
            errorTextField.setText("Bitte nur Ziffern eintragen.");
            errorTextField.setVisible(true);
        } else {
            Pattern pattern = Pattern.compile("[.|,]");
            Matcher matcher = pattern.matcher(validInput);
            matcher.find(); //find the first . or ,
            //if matcher finds a second . or , something is wrong
            if (matcher.find()) {
                Platform.runLater(() -> {
                    disburseValueTextField.setText("");
                    errorTextField.setText("Es darf maximal ein Komma oder Punkt vorkommen");
                    errorTextField.setVisible(true);
                });
            } else {
                errorTextField.setVisible(false);
            }
        }
    }

    public void disburse(Event event) {
        String input = disburseValueTextField.getText().replace(',', '.');
        try {
            double value = Double.parseDouble(input);
            if (value <= 0) {
                errorTextField.setText("Du kannst nur positive Beträge abheben.");
                errorTextField.setVisible(true);
            } else {
                BankAccount bankAccount = CustomerManager.getInstance().getInspectedBankAccount();
                if (value > bankAccount.getBalance()) {
                    errorTextField.setText("Du kannst maximal " + bankAccount.getBalance() + " abheben.");
                    errorTextField.setVisible(true);
                } else {
                    bankAccount.disburse(value);
                    goBack(event);
                }
            }
        } catch (NumberFormatException e) {
            errorTextField.setText("Bitte eine Zahl eingeben!");
            errorTextField.setVisible(true);
        }
    }
}
