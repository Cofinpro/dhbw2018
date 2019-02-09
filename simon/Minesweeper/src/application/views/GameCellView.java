package application.views;

import application.controller.GameCellController;
import application.models.GameCell;
import javafx.scene.control.Button;

import java.util.Observable;
import java.util.Observer;

public class GameCellView extends Button implements Observer {

    private GameCell gameCell;

    GameCellView(GameCell gameCell) {
        super(gameCell.toString());
        this.gameCell = gameCell;
        new GameCellController(this);
        gameCell.addObserver(this);
    }

    public GameCell getGameCell() {
        return gameCell;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.setText(gameCell.toString());
    }
}
