package model;

public enum Difficulty {
    EASY_CHEASY (0, "Easy Cheasy", 5, 7, 7), //10.2% Mines
    NORMAL (1, "Normal", 15, 10, 10), //15% Mines
    HARD (2, "Hard", 60, 20, 20), //15% Mines
    SICKO_MODE (3, "Sicko Mode", 100, 20, 30); //16.6% Mines

    private int difNumber;
    private String difName;
    private int amountMines;
    private int rows;
    private int cols;


    Difficulty(int difNumber, String difName, int amountMines, int rows, int cols) {
        this.difNumber = difNumber;
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

    public String getDifName() {
        return difName;
    }
}
