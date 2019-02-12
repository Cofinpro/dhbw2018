package application.controllers;

import application.Main;
import application.models.Result;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;

class ResultController extends HBox {

    @FXML
    private TextField rankTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField timeTextField;

    ResultController(int rank, Result result) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/result.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        rankTextField.setText("" + rank);
        nameTextField.setText(result.getName());
        timeTextField.setText("" + result.getTimeSeconds());
    }
}
