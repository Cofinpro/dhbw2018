package controller;
import javafx.scene.layout.GridPane;
import model.Cell;
import model.Game;

public class MainController {
    public GridPane gridPane;

    public void initialize(){

        Game game = Game.getInstance();
        for (int row = 0; row < game.getRows(); row++){
            for (int column = 0; column < game.getColumns();column++){
                Cell cell = game.getCell(row,column);
                GameCellController button = new GameCellController(cell);
                gridPane.add(button,column,row);
            }
        }
    }

}
