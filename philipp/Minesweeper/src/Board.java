import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board implements ActionListener {
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
    Font buttonFont = new Font("Arial", Font.PLAIN, 40);
    Font labelFont = new Font("Arial", Font.PLAIN, 40);


    public Board() {
        askForValues();
        initializeView();
    }

    public void askForValues() {
        Object[] options = {"Easy Cheasy",
                "Normal",
                "Hard",
                "Sicko Mode",
                "I surrender"};
        int n = JOptionPane.showOptionDialog(frame,
                "Please select a difficulty",
                "Choose Difficulty Level",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[4]);

        switch (n) {
            case 0:
                amountMines = 5;
                rows = 7;
                cols = 7;
                break;
            case 1:
                amountMines = 15;
                rows = 10;
                cols = 10;
                break;
            case 2:
                amountMines = 50;
                rows = 20;
                cols = 20;
                break;
            case 3:
                amountMines = 160;
                rows = 25;
                cols = 25;
                break;
            case 4:
                System.exit(0);
        }
    }

    public void initializeView() {
        amountMinesLeft = amountMines;
        frame = new JFrame("Minesweeper");
        reset = new JButton("Reset");
        leftMinesTextField = new JTextField();
        leftMinesTextField.setFont(labelFont);
        leftMinesTextField.setText(String.valueOf(amountMinesLeft));
        leftMinesTextField.enableInputMethods(false);
        top.setLayout(new BorderLayout());
        top.add(leftMinesTextField, BorderLayout.WEST);
        top.add(reset, BorderLayout.EAST);
        buttons = new JButton[rows][cols];
        tiles = new Tile[rows][cols];

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

    private void resetGame() {
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

    private boolean isInside(int r, int c)
    {
        return r >= 0 && c >= 0 && r < tiles.length && c < tiles[r].length;
    }

    public static void main(String[] args) {
        Board board = new Board();
        board.resetGame();
    }
}
