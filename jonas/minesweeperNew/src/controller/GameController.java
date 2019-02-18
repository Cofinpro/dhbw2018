package controller;

import model.Cell;
import model.Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameController extends MouseAdapter {

    private Game game;
    private Cell cell;

    public GameController(Cell cell){
        this.cell = cell;
    }

    public GameController(Game game){
        this.game = game;
    }

    public void mouseClicked(MouseEvent e) {

            switch (e.getButton()) {
                case MouseEvent.BUTTON1:
                    if (this.game == null) {
                        if (!cell.isMarked()) {
                            cell.revealTheCell();

                        }
                    } else {
                        game.initialize();
                    }
                    break;
                case MouseEvent.BUTTON3:
                    if (this.game == null) {
                        cell.changeStateOfCell();
                    }
                    break;
                default:
                    break;
            }
        }
    }

