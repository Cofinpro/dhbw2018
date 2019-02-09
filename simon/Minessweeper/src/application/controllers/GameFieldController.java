package application.controllers;

import application.Main;
import application.models.GameCell;
import application.models.GameField;
import application.models.Settings;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class GameFieldController extends GridPane {
    public GameFieldController(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/gameField.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        Settings settings = Settings.getInstance();
        settings.getDifficultyProperty().addListener(event -> updateGameField());
    }

    private void updateGameField() {
        clearGameField();
        GameField gameField = new GameField();
        int length = gameField.getLength();
        for (int row = 0; row < length; row++) {
            for (int column = 0; column < length; column++) {
                GameCell gameCell = gameField.getGameCell(row, column);
                Button button = new GameCellController(gameCell);
                this.add(button, row, column);
            }
        }
    }

    private void clearGameField() {
        this.getChildren().clear();
    }
}
