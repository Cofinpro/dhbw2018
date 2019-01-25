package models;

import java.util.ArrayList;
import java.util.List;

public class GameOfLife {
    private List<GameSituation> situationList; //all situations including the current one

    private GameOfLife() {
        situationList = new ArrayList<GameSituation>();
        situationList.add(new GameSituation(15,15));
    }

    public GameSituation getCurrentGameSituation() {
        System.out.println(situationList.size());
        return situationList.get(situationList.size()-1);
    }

    public void moveToNextSituation() {
        GameSituation upcomingSituation = getCurrentGameSituation().getNextGameSituation();
        situationList.add(upcomingSituation);
    }

    /**
     * moves to the last situation if there is one
     */
    public void tryToMoveToLastSituation() {
        if (situationList.size() < 2) {
            return;
        }
        situationList.remove(situationList.size()-1); //removes current situation
    }

    public static GameOfLife getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        public static GameOfLife INSTANCE = new GameOfLife();
    }

    /**
     * This method switches a cell on or off. As this situation no longer just evolves from previous situations but from manipulation, all previous situation are deleted to avoid confusion.
     * @param row the row of the cell
     * @param column the column of the cell
     */
    public void switchCellAndDeletePreviousSituations(int row, int column) {
        GameSituation currentSituation = getCurrentGameSituation();
        currentSituation.switchCell(row, column);
        //now the previous situations did not cause this situation because the user made manipulations, we have to delete them
        situationList.clear();
        situationList.add(currentSituation);
    }

    public void toConsole() {
        System.out.println(getCurrentGameSituation());
    }

    public boolean isCellAliveAt(int row, int column) {
        GameSituation currentSituation = getCurrentGameSituation();
        return currentSituation.isCellAliveAt(row, column);
    }
}
