package application.enums;

import java.util.Random;

public enum Difficulty {
    EASY("easy",9, 9 ,2),
    MEDIUM("medium",16, 16, 4);

    private int bombCount;
    private String representation;
    private int fieldRows;
    private int fieldColumns;

    Difficulty(String representation, int fieldRows, int fieldColumns, int bombCount) {
        this.representation = representation;
        this.bombCount = bombCount;
        this.fieldRows = fieldRows;
        this.fieldColumns = fieldColumns;
    }

    public static Difficulty getDifficultyByRepresentation(String representation) {
        for (Difficulty difficulty : Difficulty.values()) {
            if (difficulty.toString().equals(representation)) {
                return difficulty;
            }
        }
        return null;
    }

    public int getBombCount() {
        return bombCount;
    }

    public int getFieldRows() {
        return fieldRows;
    }

    public int getFieldColumns() {
        return fieldColumns;
    }

    @Override
    public String toString() {
        return representation;
    }
}
