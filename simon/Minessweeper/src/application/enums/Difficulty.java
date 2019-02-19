package application.enums;

public enum Difficulty {
    EASY("easy",9, 9 ,10, 0),
    MEDIUM("medium",16, 16, 40, 0),
    DIFFICULT("difficult", 16, 30, 99, 0),
    INSANE("insane", 16, 30, 89, 10);

    private int simpleBombCount;
    private String representation;
    private int fieldRows;
    private int fieldColumns;
    private int superBombCount;

    Difficulty(String representation, int fieldRows, int fieldColumns, int simpleBombCount, int superBombCount) {
        this.representation = representation;
        this.simpleBombCount = simpleBombCount;
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
        throw new IllegalArgumentException("There is no difficulty " + representation);
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

    public String getDescription() {
        return fieldRows + "x" + fieldColumns + " - " + simpleBombCount + " bombs - " + superBombCount + " super bombs";
    }
}
