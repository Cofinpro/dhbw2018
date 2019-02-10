package application.controllers;

import application.Main;
import application.models.GameCell;
import application.models.Game;
import application.models.RepresentableGameCell;
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
        Game game = Game.getInstance();
        game.setup();
        for (int row = 0; row < game.getRowCount(); row++) {
            for (int column = 0; column < game.getColumnCount(); column++) {
                RepresentableGameCell gameCell = game.getGameCell(row, column);
                Button button = new GameCellController(gameCell);
                this.add(button, row, column);
            }
        }
    }

    private void clearGameField() {
        this.getChildren().clear();
    }
}
