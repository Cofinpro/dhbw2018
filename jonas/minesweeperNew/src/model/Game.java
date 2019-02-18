package model;

import java.util.Observable;

public class Game extends Observable {
    private Cell[][] cells;
    private int rows;
    private int columns;
    private int bombs;
    private int amountBombsLeft;
    private String state;
    private int amountRevealed;
    private Thread thread;
    private int timer;
    private boolean running;

    public Game( int rows,int columns, int bombs){
        this.cells = new Cell[rows][columns];
        this.rows = rows;
        this.columns = columns;
        this.bombs = bombs;
        this.amountBombsLeft = bombs;
        this.amountRevealed = 0;
        this.timer = 0;
        this.state = "running";
        this.running = false;

        buildGame();
    }

    public void initialize(){
        this.state = "lost";
        this.setChanged();
        this.notifyObservers();

        resetCells();
        this.state = "running";
        this.amountBombsLeft = this.bombs;
        this.amountRevealed = 0;

        resetThread();
        buildGame();
        this.setChanged();
        this.notifyObservers();

    }


    public void revealZeros(Cell cells){
        int x = cells.getxPos();
        int y = cells.getyPos();

        int ax;
        int ay;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                ay = y + i;
                ax = x + j;

                if (ay >= 0 && ay < this.rows && ax >= 0 && ax < this.columns) {
                    if (!this.cells[ay][ax].isMarked()) {
                        this.cells[ay][ax].revealTheCell();
                    }
                }

            }
        }

    }


    public void bombCounter(Cell cells){
        int xAxis;
        int yAxis;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                xAxis = cells.getxPos() + j;
                yAxis = cells.getyPos() + i;
                if (xAxis >= 0 &&xAxis < this.columns && yAxis >=0 && yAxis < this.rows){
                    if (this.cells[yAxis][xAxis].getCellID() !=9){
                        this.cells[yAxis][xAxis].addOne();
                    }
                }
            }
        }

    }


    private void setThread(){
        this.thread = new Thread(){
            @Override
            public void run() {
                while (running) {
                    try {
                        addTimer();
                        sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        };
        this.thread.start();
    }

    private void addTimer() {
        this.timer++;
        this.setChanged();
        this.notifyObservers();
    }

    public void buildGame(){
        setCells();


        for (int i = 0; i < bombs; i++) {
            int width, height;

            do {

                width = (int) (Math.random() * (this.columns));
                height = (int) (Math.random() * (this.rows));

            } while (this.cells[height][width].getCellID() == 9);

            this.cells[height][width].setBomb();
            bombCounter(getCell(height, width));
        }
    }

    private void setCells(){
        for (int i = 0; i < this.rows;i++){
            for (int j = 0; j < this.columns;j++){
                this.cells[i][j]= new Cell(this,j,i,0);
            }
        }
    }
    public void resetCells(){
        for (int i = 0; i < this.rows;i++){
            for (int j = 0; j < this.columns;j++){
                this.cells[i][j].initialize(this, j , i,0);
            }
        }
    }

    public void setState(String state){
        this.state = state;
        this.setChanged();
        this.notifyObservers();
    }
    public void addToRevealed(){
        this.amountRevealed++;
        if (this.amountRevealed >= (this.rows * this.columns)-bombs){
            setState("Gewonnen");
        }
        this.setChanged();
        this.notifyObservers();
    }

    public void subRemainingBombs(){
        this.amountBombsLeft--;
        this.setChanged();
        this.notifyObservers();
    }
    public void addRemainingBombs(){
        this.amountBombsLeft++;
        this.setChanged();;
        this.notifyObservers();
    }

    public void startThread(){
        this.running = true;
        this.setThread();
    }

    public void tryStartThread(){
        if (this.running == false){
            startThread();
        }
    }
    public void resetThread(){
        this.running = false;
        this.timer = 0;
        this.setChanged();
        this.notifyObservers();
    }
    public void stopThread(){
        this.running = false;
    }


    public int getRows(){
        return this.rows;
    }
    public int getColumns(){
        return this.columns;
    }
    public String getState() {
        return this.state;
    }

    public Cell getCell(int rows, int columns){
        return this.cells[rows][columns];
    }

    public int getAmountBombsLeft(){
        return this.amountBombsLeft;
    }

    public int getTimer(){
        return this.timer;
    }

}

