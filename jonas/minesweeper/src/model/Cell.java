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

    public int getAoumtOfBombsNearby(){
        return 0;
    }

    public void changeStateToChecked(){
        // wenn isBomb und isCleared falsch sind isCleared auf true
        // wenn isBomb falsch ist und isCleared true Fenster ausgeben mit dem Hinweis das dieses Feld schon aufgedeckt ist
        // wenn isBomb true und isCleared false isCleared auf true und game Verloren
    }
    public void changeStateToCheckedBomb(){
        // wenn isCleared falsch ist isCleared false aber Image auf Button ausgeben
    }

    public void printAmountNearbyLivingBombs(){
        // wenn isChecked falsch print getAmountOfBombsNearby auf dem Knopf ausgeben
    }

}
