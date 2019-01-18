package models;

import sample.GameOfLifeView;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class GameOfLife {
    private List<GameSituation> situationList;
    //the view which displays this Game Of Life
    private GameOfLifeView gameOfLifeView;

    private GameOfLife() {
        situationList = new ArrayList<GameSituation>();
        situationList.add(new GameSituation());
    }

    public void setGameOfLifeView(GameOfLifeView gameOfLifeView) {
        this.gameOfLifeView = gameOfLifeView;
    }

    public GameSituation getCurrentGameSituation() {
        return situationList.get(situationList.size()-1);
    }

    public void moveToNextSituation() {
        situationList.add(getCurrentGameSituation().next());
        if (gameOfLifeView != null) {
            gameOfLifeView.display();
        }
    }

    public void moveToLastSituation() {
        if (situationList.size() < 2) {
            return;
        }
        situationList.remove(situationList.size()-1);
        if (gameOfLifeView != null) {
            gameOfLifeView.display();
        }
    }

    public static GameOfLife getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        public static GameOfLife INSTANCE = new GameOfLife();
    }
}
