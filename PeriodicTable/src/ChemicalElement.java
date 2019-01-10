public class ChemicalElement implements Comparable<ChemicalElement> {
    private int atomicNumber;
    private String name;
    private String symbol;
    private int period;
    private int group;
    private static boolean[] metalloid;

    public ChemicalElement(int atomicNumber, String name, String symbol, int period, int group) {
        this.atomicNumber = atomicNumber;
        this.name = name;
        this.symbol = symbol;
        this.period = period;
        this.group = group;
        metalloid = new boolean[117];
        for (int i = 0; i < metalloid.length; i++) {
            metalloid[i] = false;
        }
        metalloid[5] = metalloid[14] = metalloid[32] = metalloid[33] = metalloid[51] = metalloid[52] = metalloid[84] = metalloid[85] = true;
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

    public boolean isAlkali() {
        return (group == 1) && (atomicNumber != 1);
    }

    public boolean isMetalloid() {
        return metalloid[atomicNumber-1];
    }

    @Override
    public int compareTo(ChemicalElement o) {
        return atomicNumber - o.atomicNumber;
    }
}
