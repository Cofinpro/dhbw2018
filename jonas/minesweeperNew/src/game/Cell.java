package game;

import java.util.Observable;

public class Cell extends Observable {

    private int cellID;
    private int xPos;
    private int yPos;
    private Game game;
    private boolean isRevealed;
    private boolean isMarked;

    public Cell(Game game, int xPos, int yPos, int cellID){
        this.game = game;
        this.xPos = xPos;
        this.yPos = yPos;
        this.cellID = cellID;
        this.isRevealed = false;
        this.isMarked = false;
    }

    public void initialize(Game game, int xPos, int yPos, int cellID){
        this.game = game;
        this.xPos = xPos;
        this.yPos = yPos;
        this.cellID = cellID;
        this.isRevealed = false;
        this.isMarked = false;
    }

    public void revealTheCell(){
        if (game.getState().equals("running")) {
                this.game.tryStartThread();



            if (this.cellID == 9){
                this.game.setState("lost");
                this.isRevealed = true;
                this.setChanged();
                this.notifyObservers();
            }
            else if(this.isRevealed == false){
                game.addToRevealed();
                this.isRevealed = true;
                if (this.cellID == 0){
                    this.game.revealZeros(this);
                }
                this.setChanged();
                this.notifyObservers();
            }

            if(!game.getState().equals("running")){
                this.game.stopThread();
            }
        }
    }

    public void changeStateOfCell(){
        if (!this.isMarked && !this.isRevealed() && game.getState().equals("running")){

                this.game.tryStartThread();

            this.isMarked = true;
            this.game.subRemainingBombs();
            this.setChanged();
            this.notifyObservers();
        }
        else if(!this.isRevealed() && game.getState().equals("running")){
            this.isMarked = false;
            this.game.addRemainingBombs();
            this.setChanged();
            this.notifyObservers();
        }
        if (!game.getState().equals("running")){
            this.game.stopThread();
        }
    }



    public void setBomb() {
        this.cellID = 9;
    }

    public int getCellID() {
        return cellID;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public Game getGame() {
        return this.game;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public boolean isMarked() {
        return this.isMarked;
    }
    public void addOne(){
        this.cellID++;
    }
}
