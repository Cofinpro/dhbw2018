package model;

public class Cell {

    private boolean isExplosive;
    private boolean isMarked;
    private boolean isCleared;
    private Game game;
    private int rows;
    private int columns;

    Cell(boolean getExplosive, Game game, int rows , int columns){
        this.isExplosive = isExplosive;
        this.game = game;
        this.rows = rows;
        this.columns = columns;
    }

    public boolean getMarkedState(){
        return isMarked;
    }

    public void setMarkedState(){
        this.isMarked = true;
    }

    public boolean getState(){
        return isCleared;
    }

    public int getAoumtOfBombsNearby(){
        int result = 0;
        Cell[][] cells = new Cell[rows][columns];
        if (game.getRows() - 1 >= 0 && game.getColumns() - 1 >= 0 && game.getRows() <= rows && game.getColumns() <= columns) {
            if (cells[game.getRows() - 1][game.getColumns() - 1].isExplosive == true) {
                result++;
            }
            if (cells[game.getRows() - 1][game.getColumns()].isExplosive == true) {
                result++;
            }
            if (cells[game.getRows() - 1][game.getColumns() + 1].isExplosive == true) {
                result++;
            }

            if (cells[game.getRows() + 1][game.getColumns() + 1].isExplosive == true) {
                result++;
            }
            if (cells[game.getRows() + 1][game.getColumns()].isExplosive == true) {
                result++;
            }
            if (cells[game.getRows() + 1][game.getColumns() - 1].isExplosive == true) {
                result++;
            }

            if (cells[game.getRows()][game.getColumns() + 1].isExplosive == true) {
                result++;
            }
            if (cells[game.getRows()][game.getColumns() - 1].isExplosive == true) {
                result++;
            }
        }
        return result;
    }

    public void tryChangeStateToCleared(){

        if (!isExplosive && !isCleared){
            this.isCleared = true;
        }
        if (!isExplosive && isCleared){
            System.out.println("Already checked");
        }
        if (isExplosive && !isCleared){
            System.out.println("Game lost");
        }
    }

}
