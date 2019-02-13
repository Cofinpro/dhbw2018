package application.enums;

public enum Difficulty {
    EASY("easy",9, 9 ,5, 0),
    MEDIUM("medium",16, 16, 7, 1);

    private int simpleBombCount;
    private String representation;
    private int fieldRows;
    private int fieldColumns;
    private int superBombCount;

    Difficulty(String representation, int fieldRows, int fieldColumns, int bombCount, int superBombCount) {
        this.representation = representation;
        this.simpleBombCount = bombCount;
        this.fieldRows = fieldRows;
        this.fieldColumns = fieldColumns;
        this.superBombCount = superBombCount;
    }

    public static Difficulty getDifficultyByRepresentation(String representation) {
        for (Difficulty difficulty : Difficulty.values()) {
            if (difficulty.toString().equals(representation)) {
                return difficulty;
            }
        }
        return null;
    }

    public int getBombCountOverall() {
        return simpleBombCount + superBombCount;
    }

    public int getSimpleBombCount() {
        return simpleBombCount;
    }

    public int getSuperBombCount() {
        return superBombCount;
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
