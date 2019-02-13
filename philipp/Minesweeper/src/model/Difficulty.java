package model;

public enum Difficulty {
    EASY_CHEASY ("Easy_Cheasy", 5, 7, 7),
    NORMAL ("Normal", 15, 10, 10),
    HARD ("Hard", 50, 20, 20),
    SICKO_MODE ("Sicko Mode", 150, 20, 30);

    private String difName;
    private int amountMines;
    private int rows;
    private int cols;


    Difficulty(String difName, int amountMines, int rows, int cols) {
        this.difName = difName;
        this.amountMines = amountMines;
        this.rows = rows;
        this.cols = cols;
    }

    public int getAmountMines() {
        return amountMines;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
