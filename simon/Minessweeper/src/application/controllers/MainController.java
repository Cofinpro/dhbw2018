package application.controllers;

import application.enums.GameState;
import application.models.Game;
import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;


public class MainController {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField textField;

    @FXML
    public void initialize() {
        textField.textProperty().bind(Game.getInstance().getRevealedCellCountProperty().asString());
        Game.getInstance().getGameStateProperty().addListener(event -> {
            if (Game.getInstance().getGameState() == GameState.WON) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("You won");
                alert.setContentText("Well done!");
                alert.showAndWait();
            }
        });
    }
}
