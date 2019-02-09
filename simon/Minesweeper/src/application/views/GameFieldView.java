package application.views;

import application.models.GameCell;
import application.models.GameField;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GameFieldView extends GridPane {
    public GameFieldView(GameField gameField) {
        super();
        for (int i = 0; i < gameField.getRowCount(); i++) {
            for (int j = 0; j < gameField.getColumnCount(); j++) {
                GameCell gameCell = gameField.getGameCell(i, j);
                this.add(new GameCellView(gameCell), i, j);
            }
        }
    }
}
