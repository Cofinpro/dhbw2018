package Chemical;

public class ChemicalElement implements Comparable<ChemicalElement>{
    int row;
    int column;
    int atomicnumber;
    String symbol;
    String name;

    public ChemicalElement(int atomicnumber, String symbol,String name,int row,int column){
        this.atomicnumber = atomicnumber;
        this.symbol = symbol;
        this.name = name;
        this.row = row;
        this.column = column;


    }
    public ChemicalElement(){

    }
    public ChemicalElement(int atomicnumber){
        this.atomicnumber = atomicnumber;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getAtomicnumber() {
        return atomicnumber;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return ""+atomicnumber + ":  " + symbol+", "+ name + ", " + row +", "+ column + "";
    }

    public boolean isFluid(){
        return  (row ==4 && column ==17) ||
                (row ==6 && column==12)||
                (row==7 && column==12);
    }
    public boolean isGas(){
        switch (atomicnumber){
            case 7:
            case 8:
            case 9:
            case 10:
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

    static boolean isEarthAkaliBool[]= new boolean[118];
    public boolean isEarthAkali(){
        isEarthAkaliBool[3] = true;
        isEarthAkaliBool[11] = true;
        isEarthAkaliBool[19] = true;
        isEarthAkaliBool[37] = true;
        isEarthAkaliBool[55] = true;
        isEarthAkaliBool[87] = true;
        return isEarthAkaliBool[atomicnumber-1];
    }

    @Override
    public int compareTo(ChemicalElement o) {
        if (o == null){
            return 1;
        }
        if (this.atomicnumber == o.atomicnumber){
            return 0;
        }
        if (this.atomicnumber > o.atomicnumber ){
            return 1;
        }
            return -1;
    }
}
