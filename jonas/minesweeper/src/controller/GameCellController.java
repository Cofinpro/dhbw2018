package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import model.Cell;
import views.Main;

import java.io.IOException;

public class GameCellController extends Button {
    GameCellController(Cell cell){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gameCells.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY){
                cell.tryChangeStateToCleared();
            }
            else if (event.getButton() == MouseButton.SECONDARY){
                cell.setMarkedState();
            }
        });
    }
}
