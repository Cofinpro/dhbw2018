public class ChemicalElement implements Comparable<ChemicalElement>{

    private int atomicNumber;
    private String symbol;
    private String name;
    private int period;
    private int group;

    /**
     * Constructor for an element
     */
    public ChemicalElement(int atomicNumber, int period, int group, String symbol, String name){
       this.atomicNumber = atomicNumber;
       this.period = period;
       this.group = group;
       this.symbol = symbol;
       this.name = name;
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }

    public boolean isAlkali(int atomicNumber){
        boolean[] alkaliArray = new boolean[119];
        for(int count=1; count<alkaliArray.length; count++)
            alkaliArray[count] = false;

        alkaliArray[3] = alkaliArray[11] = alkaliArray[19] = alkaliArray[37] = alkaliArray[55] = alkaliArray[87] = true;

        return alkaliArray[atomicNumber];

    }

    public boolean isFluid(int period, int group){
        return (period == 4 && group == 17)
                || (period == 6 && group == 12)
                || (period == 7 && group == 12);
    }

    public boolean isGas(int atomicNumber){
        switch (atomicNumber){
            case 1:
            case 2:
            case 7:
            case 8:
            case 9:
            case 10:
            case 17:
            case 18:
            case 36:
            case 54:
            case 86:
            case 118:
                return true;
            default:
                return false;
        }
    }

    @Override
    public int compareTo(ChemicalElement chemicalElement){
        if(this.atomicNumber == chemicalElement.atomicNumber)
            return 0;
        if(this.atomicNumber < chemicalElement.atomicNumber)
            return -1;
        return 1;
    }

    public String toString(){
        return "" + name;
    }
}
