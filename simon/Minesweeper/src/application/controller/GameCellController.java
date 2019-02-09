package application.controller;

import application.views.GameCellView;

public class GameCellController {
    public GameCellController(GameCellView gameCellView) {
        gameCellView.setOnAction(e -> gameCellView.getGameCell().reveal());
    }
}
