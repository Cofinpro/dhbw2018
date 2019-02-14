package model;

import view.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board {

    private static Board instance;

    int amountMines;
    int amountMinesLeft;
    int rows;
    int cols;
    JFrame frame;
    JButton reset;
    JTextField leftMinesTextField;
    JButton[][] buttons;
    Tile[][] tiles;
    Container grid = new Container();
    Container top = new Container();
    Font labelFont = new Font("Arial", Font.PLAIN, 40);
    GameSettings gameSettings = GameSettings.getInstance();
    Difficulty difficulty = gameSettings.getDifficulty();


    private Board() {
        amountMines = difficulty.getAmountMines();
        amountMinesLeft = amountMines;
        rows = difficulty.getRows();
        cols = difficulty.getCols();
    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public void onClickAction(ActionEvent event) {
        if (event.getSource().equals(reset)) {
            resetGame();
        }
        else {
            for (int x = 0; x < buttons.length; x++) {
                for (int y = 0; y < buttons[x].length; y++) {
                    if (event.getSource().equals(buttons[x][y])) {
                        int modifiers = event.getModifiers();

                        if (modifiers == 17) {
                            switchFlagged(x, y);
                        }
                        else {
                            buttons[x][y].setText(tiles[x][y].toString());

                            if (tiles[x][y].isMine()) {
                                loseGame();
                            } else if (tiles[x][y].getNearbyMines() == 0) {
                                openZeros(x, y);
                            }

                            tiles[x][y].setRevealed(true);
                            buttons[x][y].setEnabled(false);
                            checkWin();

                        }
                    }
                }
            }
        }
    }


    public int getAmountMinesLeft() {
        return amountMinesLeft;
    }

    public void switchFlagged(int x, int y) {
        if (tiles[x][y].isFlagged()) {
            tiles[x][y].setFlagged(false);
            buttons[x][y].setText(null);
            amountMinesLeft++;
        }
        else{
            tiles[x][y].setFlagged(true);
            buttons[x][y].setText(tiles[x][y].toString());
            amountMinesLeft--;
        }
        leftMinesTextField.setText(String.valueOf(amountMinesLeft));

    }

    public void checkWin() {
        boolean win = true;
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                if (!tiles[x][y].isRevealed() && !tiles[x][y].isMine())
                    win = false;
            }
        }
        if (win) {
            JLabel label = new JLabel("you won!");
            label.setFont(labelFont);
            JOptionPane.showMessageDialog(frame, label);
        }
    }

    public void openZeros(int row, int col) {
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if (isInside(r, c) && !tiles[r][c].isRevealed()) {
                    buttons[r][c].setText(tiles[r][c].toString());
                    buttons[r][c].setEnabled(false);
                    tiles[r][c].setRevealed(true);

                    if (tiles[r][c].getNearbyMines() == 0)
                        openZeros(r, c);
                }
            }
        }
    }

    public void loseGame() {
        for (int x = 0; x < buttons.length; x++) {
            for (int y = 0; y < buttons[x].length; y++) {
                buttons[x][y].setText(tiles[x][y].toString());
                buttons[x][y].setEnabled(false);
            }
        }
        JLabel label = new JLabel("you lost!");
        label.setFont(labelFont);
        JOptionPane.showMessageDialog(frame, label);
    }

    private void createRandomMines(int amountMines) {
        for (int i = 0; i < amountMines; i++) {
            int randX = (int)(Math.random() * tiles.length);
            int randY = (int)(Math.random() * tiles[randX].length);
            if (tiles[randX][randY].isMine())
                i--;
            else
                tiles[randX][randY].setMine(true);
        }
    }

    private int countNearbyMines(int row, int col) {

        if (tiles[row][col].isMine())
            return -1;

        int mineCount = 0;
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if (isInside(r, c) && tiles[r][c].isMine())
                    mineCount++;
            }
        }
        return mineCount;
    }

    public void resetGame() {
        amountMinesLeft = amountMines;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new Tile();
            }
        }
        createRandomMines(amountMines);

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                int count = countNearbyMines(x, y);
                tiles[x][y].setNearbyMines(count);
                buttons[x][y].setText(null);
                buttons[x][y].setEnabled(true);
            }
        }
    }

    private boolean isInside(int r, int c) {
        return r >= 0 && c >= 0 && r < tiles.length && c < tiles[r].length;
    }

    public static void main(String[] args) {
        Board board = new Board();
        board.resetGame();
    }
}
