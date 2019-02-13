package view;

import model.Board;
import model.Difficulty;
import model.GameSettings;
import model.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener {

    private static Game instance;

    static JFrame frame = new JFrame();
    static JButton reset = new JButton();
    static JTextField leftMinesTextField = new JTextField();
    static Font buttonFont = new Font("Arial", Font.PLAIN, 40);
    static Font labelFont = new Font("Arial", Font.PLAIN, 40);
    static Board board = Board.getInstance();
    static GameSettings gameSettings = GameSettings.getInstance();
    static Difficulty difficulty = gameSettings.getDifficulty();


    private Game() {

    }

    public static Game getInstance() {
        if (instance == null)
            instance = new Game();
        return instance;
    }

    public void initializeView() {
        int rows = difficulty.getRows();
        int cols = difficulty.getCols();
        Container top = new Container();
        Container grid = new Container();
        frame = new JFrame("Minesweeper");
        reset = new JButton("Reset");
        leftMinesTextField = new JTextField();
        leftMinesTextField.setFont(labelFont);
        leftMinesTextField.setText(String.valueOf(board.getAmountMinesLeft()));
        leftMinesTextField.enableInputMethods(false);
        top.setLayout(new BorderLayout());
        top.add(leftMinesTextField, BorderLayout.WEST);
        top.add(reset, BorderLayout.EAST);
        JButton[][] buttons = new JButton[rows][cols];
        Tile[][] tiles = new Tile[rows][cols];

        frame.setSize(1000, 1000);
        frame.setLayout(new BorderLayout());
        frame.add(top, BorderLayout.NORTH);
        reset.addActionListener(this);
        reset.setFont(buttonFont);

        grid.setLayout(new GridLayout(rows,cols));

        for (int i = 0; i <buttons.length ; i++) {
            for (int j = 0; j <buttons[i].length ; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(buttonFont);
                buttons[i][j].addActionListener(this);
                grid.add(buttons[i][j]);
                tiles[i][j] = new Tile();
            }
        }
        frame.add(grid, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        board.onClickAction(event);
    }
}
