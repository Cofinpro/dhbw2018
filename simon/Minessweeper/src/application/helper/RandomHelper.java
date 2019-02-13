package application.helper;

import application.models.Game;
import application.models.RepresentableGameCell;
import application.models.Settings;
import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;

import java.util.*;

public class RandomHelper {
    public static RepresentableGameCell[][] getGameField(Game game) {
        int rowCount = Settings.getInstance().getDifficulty().getFieldRows();
        int columnCount = Settings.getInstance().getDifficulty().getFieldColumns();
        int gameCellCount = rowCount * columnCount;
        int simpleBombCount = Settings.getInstance().getDifficulty().getSimpleBombCount();
        int superBombCount = Settings.getInstance().getDifficulty().getSuperBombCount();
        List<Integer> bombIndexes = createNewBombIndexes(gameCellCount, simpleBombCount);
        List<Integer> superBombIndexes = createNewBombIndexesButExclude(bombIndexes, gameCellCount, superBombCount);
        RepresentableGameCell[][] gameCells =  new RepresentableGameCell[rowCount][columnCount];
        int i = 0;
        int superBombsCreated = 0;
        int superBombsToCreate = Settings.getInstance().getDifficulty().getSuperBombCount();
        for (int row = 0; row < gameCells.length; row++) {
            for (int column = 0; column < gameCells[row].length; column++) {
                RepresentableGameCell gameCell;
                if (bombIndexes.contains(i)) {
                    gameCell = new RepresentableGameCell(1, game, row, column);
                } else if (superBombIndexes.contains(i)) {
                    gameCell = new RepresentableGameCell(2, game, row, column);
                } else {
                    gameCell = new RepresentableGameCell(0, game, row, column);
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

    private static List<Integer> createNewBombIndexesButExclude(List<Integer> exclude, int gameCellCount, int superBombCount) {
        List<Integer> gameCellIndexes = new ArrayList<>(gameCellCount);
        for (int i = 0; i < gameCellCount; i++) {
            gameCellIndexes.add(i);
        }
        gameCellIndexes.removeAll(exclude);
        return createNewBombIndexes(gameCellIndexes, superBombCount);
    }

    private static List<Integer> createNewBombIndexes(int gameCellCount, int bombCount) {
        List<Integer> gameCellIndexes = new ArrayList<>(gameCellCount);
        for (int i = 0; i < gameCellCount; i++) {
            gameCellIndexes.add(i);
        }
        return createNewBombIndexes(gameCellIndexes, bombCount);
    }

    private static List<Integer> createNewBombIndexes(List<Integer> gameCellIndexes, int bombCount) {
        Collections.shuffle(gameCellIndexes);
        return gameCellIndexes.subList(0, bombCount);
    }
}
