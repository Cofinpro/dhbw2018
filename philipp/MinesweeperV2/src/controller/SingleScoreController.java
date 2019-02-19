package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import view.MainFXML;

import java.io.IOException;

public class SingleScoreController extends HBox {

    @FXML
    private TextField playerNameTextField;

    @FXML
    private TextField highScoreTextField;

    @FXML
    public void initialize() {

    }

    public SingleScoreController() {
        FXMLLoader loader = new FXMLLoader(MainFXML.class.getResource("singleScoreView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTextFields(String name, String highScore) {
        playerNameTextField.setText(name);
        highScoreTextField.setText(highScore);
    }

}
