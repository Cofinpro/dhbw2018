package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import model.Cell;
import model.Game;
import sun.applet.Main;

import java.io.IOException;

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
