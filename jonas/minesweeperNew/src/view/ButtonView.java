package view;

import controller.GameController;
import model.Cell;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ButtonView extends JButton implements Observer {
    private JButton button;
    private Cell cells;
    private GameController controller;

    public ButtonView(Cell cells){
        this.cells = cells;
        this.button = new JButton("");
        this.button.setPreferredSize(new Dimension(50,50));
        this.controller = new GameController(cells);
        this.button.addMouseListener(controller);
        this.cells.addObserver(this);
    }
    public JButton getButton(){
        return this.button;
    }


    @Override
    public void update(Observable o, Object arg) {
        if (cells.isRevealed() == true){

            if (this.cells.getCellID() == 9){
                this.button.setBackground(Color.red);
                this.button.setText("B");
            } else {
                this.button.setBackground(Color.lightGray);
                if (this.cells.getCellID()==0){
                    this.button.setText("");
                } else{
                    this.button.setText(Integer.toString(this.cells.getCellID()));
                }
            }
        }
        if(cells.isMarked()){
            this.button.setText("!");
            this.button.setBackground(Color.orange);
        }
        if (!cells.isMarked() && !cells.isRevealed()){
            this.button.setText("");
            this.button.setBackground(new JButton().getBackground());
        }
    }
}
