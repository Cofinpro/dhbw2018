package application.controllers;

import application.Main;
import application.models.GameCell;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

class GameCellController extends Button {
    GameCellController(GameCell gameCell) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/gameCell.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        setOnAction(event -> gameCell.reveal());
        this.textProperty().bind(gameCell.representationProperty());
    }
}
