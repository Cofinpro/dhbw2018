package model;

public enum Difficulty {
    EASY_CHEASY (0, "Easy Cheasy", 5, 7, 7),
    NORMAL (1, "Normal", 15, 10, 10),
    HARD (2, "Hard", 50, 20, 20),
    SICKO_MODE (3, "Sicko Mode", 150, 20, 30);

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
