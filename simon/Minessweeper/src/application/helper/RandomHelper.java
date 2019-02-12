package application.helper;

import application.models.Game;
import application.models.GameCell;
import application.models.RepresentableGameCell;
import application.models.Settings;

import java.util.*;

public class RandomHelper {
    public static RepresentableGameCell[][] getGameField(Game game) {
        int rowCount = Settings.getInstance().getDifficulty().getFieldRows();
        int columnCount = Settings.getInstance().getDifficulty().getFieldColumns();
        int gameCellCount = rowCount * columnCount;
        int bombCount = Settings.getInstance().getDifficulty().getBombCount();
        List<Integer> bombIndexes = createNewBombIndexes(gameCellCount, bombCount);
        RepresentableGameCell[][] gameCells =  new RepresentableGameCell[rowCount][columnCount];
        int i = 0;
        for (int row = 0; row < gameCells.length; row++) {
            for (int column = 0; column < gameCells[row].length; column++) {
                RepresentableGameCell gameCell;
                if (bombIndexes.contains(i)) {
                    gameCell = new RepresentableGameCell(true, game, row, column);
                } else {
                    gameCell = new RepresentableGameCell(false, game, row, column);
                    gameCell.getIsRevealedProperty().addListener((observable, oldValue, newValue) -> game.getRevealedHarmlessCellCountProperty().set(game.getRevealedCellCount() +1));
                }
                gameCell.getIsSuspectedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        game.incrementSuspectedCellCount(1);
                    } else {
                        game.incrementSuspectedCellCount(-1);
                    }
                });
                gameCells[row][column] = gameCell;
                i++;
            }
        }
        return gameCells;
    }

    private static List<Integer> createNewBombIndexes(int gameCellCount, int bombCount) {
        List<Integer> gameCellIndexes = new ArrayList<>(gameCellCount);
        for (int i = 0; i < gameCellCount; i++) {
            gameCellIndexes.add(i);
        }
        Collections.shuffle(gameCellIndexes);
        return gameCellIndexes.subList(0, bombCount);

    }
}
