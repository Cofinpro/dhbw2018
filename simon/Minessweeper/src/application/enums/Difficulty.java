package application.enums;

public enum Difficulty {
    EASY("easy",4),
    MEDIUM("medium",8),
    DIFFICULT("difficult", 12);

    private String representation;
    private int fieldLength;

    Difficulty(String representation, int fieldLength) {
        this.representation = representation;
        this.fieldLength = fieldLength;
    }

    public int getFieldLength() {
        return fieldLength;
    }

    @Override
    public String toString() {
        return representation;
    }
}
