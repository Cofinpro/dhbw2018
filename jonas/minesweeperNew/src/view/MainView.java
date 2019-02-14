package view;

import controller.GameController;
import game.Game;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MainView extends JPanel implements Observer {
    private static final long serialVersionUID = 1L;
    private JPanel view;
    private JLabel bombs, gameState, timer;
    private ButtonView[][] fields;
    private Game model;

    public MainView(Game model) {
        this.model = model;
        this.setLayout(new BorderLayout());
        this.view = new JPanel();
        this.bombs = setLabel(this.bombs, "Bombs:  " + Integer.toString(model.getAmountBombsLeft()));
        this.gameState = setLabel(this.gameState, "Status:  " + model.getState());
        this.timer = setLabel(this.timer, "Time:  " + model.getTimer());

        this.add(this.bombs, BorderLayout.WEST);
        this.add(this.gameState, BorderLayout.EAST);
        this.add(this.timer, BorderLayout.CENTER);
        this.add(restartButton(), BorderLayout.NORTH);
        this.fields = new ButtonView[model.getRows()][model.getColumns()];
        this.model.addObserver(this);

        this.view.setLayout(new GridLayout(model.getRows(), model.getColumns()));
        buildbuttons();
        this.add(view, BorderLayout.SOUTH);

    }


    @Override
    public void update(Observable obs, Object o) {

        if (o != null) {
            updateButtons();
        }
        this.bombs = setLabel(this.bombs, "Bombs:  " + Integer.toString(model.getAmountBombsLeft()));
        this.gameState = setLabel(this.gameState, "Status:  " + model.getState());
        this.timer = setLabel(this.timer, "Time:  " + this.model.getTimer());

    }


    private JLabel setLabel(JLabel label, String string) {
        if (!(label instanceof JLabel)) {
            label = new JLabel("");
        }
        label.setText(string);
        label.setPreferredSize(new Dimension(100, 40));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;

    }


    public JButton restartButton() {
        JButton button = new JButton("Restart");
        button.setPreferredSize(new Dimension(20, 40));
        GameController controller = new GameController(model);
        button.addMouseListener(controller);
        return button;

    }

    public void updateButtons() {

        removeButtons();

        buildbuttons();

    }


    private void removeButtons() {
        for (int i = 0; i < this.model.getRows(); i++) {
            for (int j = 0; j < this.model.getColumns(); j++) {

                this.view.remove(fields[i][j].getButton());

            }

        }

    }

    private void buildbuttons() {

        for (int i = 0; i < this.model.getRows(); i++) {
            for (int j = 0; j < this.model.getColumns(); j++) {
                ButtonView button = new ButtonView(this.model.getCell(i, j));
                fields[i][j] = button;
                this.view.add(button.getButton());

            }

        }
    }
}

