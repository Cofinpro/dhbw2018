package rekursiv;

public class Addierer {

    public static double addSimple(double x, double y){
        return x+y;
    }
    public static Double addKomplex(Double x, Double y){
        x = new Double(12.0);
        y = new Double(14.5);

        if (x == null || y == null){
            return null;
        }
        if (y == null && x != null){
            return x;
        }
        if (y != null && x ==null){
            return y;
        }
        return x+y;
    }
    public static double addRekursiv(double x, double y){
        if (y == 0){
            return x;
        }
        if (x > 0){
            return  addRekursiv(x,y-1)+1;
        }
        throw new IllegalArgumentException();
    }
}
