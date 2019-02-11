package model;

public class Cell {

    private boolean getExplosive;
    private boolean isMarked;
    private boolean isCleared;
    private Game game;
    private int row;
    private int column;

    Cell(boolean getExplosive, Game game, int row, int column){
        this.getExplosive = getExplosive;
        this.game = game;
        this.row = row;
        this.column = column;
    }

    public boolean getMarkedState(){
        return isMarked;
    }
    public boolean getState(){
        return isCleared;
    }

}
