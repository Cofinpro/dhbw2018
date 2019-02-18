package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class SingleScoreController {

    @FXML
    private TextField playerNameTextField;

    @FXML
    private HBox scoreHbox;

    @FXML
    private TextField highScoreTextField;

    public SingleScoreController(String name, String highScore) {
        this.playerNameTextField.setText(name);
        this.highScoreTextField.setText(highScore);
        scoreHbox.getChildren().addAll(playerNameTextField, highScoreTextField);

    }

    public HBox getScoreHbox() {
        return scoreHbox;
    }

}
