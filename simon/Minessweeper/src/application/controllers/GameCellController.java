package application.controllers;

import application.Main;
import application.enums.GameState;
import application.models.GameCell;
import application.models.RepresentableGameCell;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

import java.io.IOException;

class GameCellController extends Button {
    GameCellController(RepresentableGameCell gameCell) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/gameCell.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.textProperty().bind(gameCell.representationProperty());
        this.disableProperty().bind(gameCell.getIsRevealedProperty().or(gameCell.getGame().getGameStateProperty().isNotEqualTo(GameState.PLAYING)));
        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                gameCell.tryReveal();
            } else if (event.getButton() == MouseButton.SECONDARY) {
                gameCell.switchSuspicion();
            }
        });
    }
}
