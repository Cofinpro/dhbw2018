package model;

public class Cell {

    private boolean isExplosive;
    private boolean isMarked;
    private boolean isCleared;
    private Game game;
    private int row;
    private int column;

    Cell(boolean getExplosive, Game game, int row, int column){
        this.isExplosive = isExplosive;
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

    public int getAoumtOfBombsNearby(){
        return 0;
    }

    public void tryChangeStateToCleared(){

        if (isExplosive && isCleared == false){
            this.isCleared = true;
        }
        if (isExplosive == false && isCleared == true){
            System.out.println("Already checked");
        }
        if (isExplosive == true && isCleared == false){
            System.out.println("Game lost");
        }
    }
    public void printAmountNearbyLivingBombs(){
        // wenn isChecked falsch print getAmountOfBombsNearby in Zwischenvariable speichert, die dann auf dem Knopf ausgegeben wird
    }

}
