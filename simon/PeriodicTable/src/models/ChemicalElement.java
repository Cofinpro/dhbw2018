package models;

public class ChemicalElement implements Comparable<ChemicalElement> {
    public static final int MAX_PERIOD = 7;
    public static final int MAX_GROUP = 18;
    private int atomicNumber;
    private String name;
    private String symbol;
    private int period;
    private int group;
    private static boolean[] metalloid;
    static {
        metalloid = new boolean[119];
        for (int i = 0; i < metalloid.length; i++) {
            metalloid[i] = false;
        }
        metalloid[5] = metalloid[14] = metalloid[32] = metalloid[33] = metalloid[51] = metalloid[52] = metalloid[84] = metalloid[85] = true;
    }

    public ChemicalElement(int atomicNumber, String name, String symbol, int period, int group) {
        this.atomicNumber = atomicNumber;
        this.name = name;
        this.symbol = symbol;
        this.period = period;
        this.group = group;
    }

    public boolean isFluid() {
        switch (atomicNumber) {
            case 35:
            case 80:
                return true;
            default:
                return false;
        }
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }

    public boolean isAlkali() {
        return (group == 1) && (atomicNumber != 1);
    }

    public boolean isMetalloid() {
        return metalloid[atomicNumber];
    }

    public int getPeriod() {
        return period;
    }

    public int getGroup() {
        return group;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public int compareTo(ChemicalElement o) {
        return atomicNumber - o.atomicNumber;
    }

    @Override
    public String toString() {
        String representation = "Name: " + name;
        representation += "\nSymbol: " + getSymbol();
        representation += "\nAtomic Number: " + getAtomicNumber();
        representation += "\nGroup: " + getGroup();
        representation += "\nPeriod: " + getPeriod();
        representation += "\nAlkali: " + isAlkali();
        representation += "\nFluid: " + isFluid();
        representation += "\nMetalloid: " + isMetalloid();
        representation += "\n**************************\n";
        return representation;
    }
}


