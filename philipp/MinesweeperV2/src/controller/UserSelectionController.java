package controller;

import model.Board;
import model.Difficulty;
import model.GameSettings;
import view.GameView;

import javax.swing.*;
import java.awt.*;

public class UserSelectionController {

    private static JFrame frame = new JFrame();

    private static JComboBox<Object> comboBox1;
    private static JComboBox<Object> comboBox2;

    public static void getUserInput(String title, Object[] difficultyOptions, Object[] superMineOptions) {
        frame.setTitle(title);
        frame.setSize(1000, 400);
        Font myFont = new Font("Arial", Font.PLAIN, 40);

        frame.setLayout(new BorderLayout());

        Container boxContainer = new Container();
        boxContainer.setLayout(new BorderLayout());

        comboBox1 = new JComboBox<>(difficultyOptions);
        comboBox1.setFont(myFont);

        comboBox2 = new JComboBox<>(superMineOptions);
        comboBox2.setFont(myFont);

        JButton okButton = new JButton("OK");
        okButton.setFont(myFont);
        okButton.addActionListener(e -> onOkButton());

        boxContainer.add(comboBox1, BorderLayout.NORTH);
        boxContainer.add(comboBox2, BorderLayout.SOUTH);
        frame.add(boxContainer, BorderLayout.NORTH);
        frame.add(okButton, BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void onOkButton() {
        int chosenIndex1 = comboBox1.getSelectedIndex()-1;
        int chosenIndex2 = comboBox2.getSelectedIndex()-1;

        if (comboBox1.getSelectedIndex() == 0 | comboBox2.getSelectedIndex() == 0)
            throw new IllegalArgumentException("Difficulty & Amount of Super Mines has to be selected");
        else {
            GameSettings gameSettings = GameSettings.getInstance();
            gameSettings.setDifficulty(Difficulty.values()[chosenIndex1]);
            gameSettings.setAmountSuperMines(chosenIndex2);
            frame.setVisible(false);
            Board.getInstance().resetGame();
            GameView.getInstance().initializeGameView();
        }
    }
}
