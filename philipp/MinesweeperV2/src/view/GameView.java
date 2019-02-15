package view;

import controller.GameViewController;
import model.Board;
import model.Difficulty;
import model.GameSettings;

import javax.swing.*;
import java.awt.*;

public class GameView {

    private static GameView instance;

    private GameSettings gameSettings = GameSettings.getInstance();
    private Difficulty difficulty = gameSettings.getDifficulty();

    private int rows = difficulty.getRows();
    private int cols = difficulty.getCols();

    private Font myFont = new Font("Arial", Font.PLAIN, 40);

    private JFrame frame = new JFrame("Minesweeper");
    private Container top = new Container();
    private JTextField leftMinesTextField = new JTextField();
    private JButton reset = new JButton("Reset");
    private JTextField timerTextField = new JTextField();

    private Container grid = new Container();
    private JButton[][] buttons = new JButton[rows][cols];

    private GameView () {

    }

    public void initializeGameView() {
       initializeFrameLayout();
       createButtons();
       frame.setVisible(true);
    }

    private void initializeFrameLayout() {
        leftMinesTextField.setFont(myFont);
        leftMinesTextField.setText("Mines left: " + Board.getInstance().getAmountMinesLeft());
        leftMinesTextField.enableInputMethods(false);

        top.setLayout(new BorderLayout());
        top.add(leftMinesTextField, BorderLayout.WEST);
        top.add(reset, BorderLayout.EAST);

        frame.setSize(1250, 1250);
        frame.setLayout(new BorderLayout());
        frame.add(top, BorderLayout.NORTH);
        frame.add(grid, BorderLayout.CENTER);
        frame.add(timerTextField, BorderLayout.SOUTH);
        timerTextField.setFont(myFont);
        Board.getInstance().getSeconds().addListener((observable, oldValue, newValue) -> timerTextField.setText(newValue.toString()));

        reset.addActionListener(e -> GameViewController.handleResetClick());
        reset.setFont(myFont);

        grid.setLayout(new GridLayout(rows, cols));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createButtons() {
        for (int x = 0; x <buttons.length ; x++) {
            for (int y = 0; y <buttons[x].length ; y++) {
                buttons[x][y] = new JButton();
                buttons[x][y].setFont(myFont);
                int finalX = x;
                int finalY = y;
                buttons[x][y].addActionListener(e -> GameViewController.handleTileClick(finalX, finalY, e.getModifiers()));
                grid.add(buttons[x][y]);
            }
        }
    }

    public void setButtonEnabled(int x, int y, boolean enabled) {
        buttons[x][y].setEnabled(enabled);
    }

    public void displayWin() {
        disableAllButtons();
        JLabel label = new JLabel("you won!");
        label.setFont(myFont);
        JOptionPane.showMessageDialog(frame, label);
    }

    public void displayLose() {
        disableAllButtons();
        JLabel label = new JLabel("you lost!");
        label.setFont(myFont);
        JOptionPane.showMessageDialog(frame, label);
    }

    private void disableAllButtons() {
        Board board = Board.getInstance();
        for (int x = 0; x < buttons.length; x++) {
            for (int y = 0; y < buttons[x].length; y++) {
                board.revealTile(x, y);
                buttons[x][y].setText(board.getTileLabel(x, y));
                buttons[x][y].setEnabled(false);
            }
        }
    }

    public void resetGame() {
        for (int x = 0; x < buttons.length; x++) {
            for (int y = 0; y < buttons[x].length; y++) {
                changeButtonText(x, y, null);
                setButtonEnabled(x, y, true);
                leftMinesTextField.setText("Mines left: " + (Board.getInstance().getAmountMinesLeft()));
            }
        }
    }

    public void changeButtonText(int x, int y, String text) {
        buttons[x][y].setText(text);
    }

    public void changeLeftMinesTextField(String number) {
        leftMinesTextField.setText(number);
    }

    public static GameView getInstance() {
        if (instance == null)
            instance = new GameView();
        return instance;
    }
}
