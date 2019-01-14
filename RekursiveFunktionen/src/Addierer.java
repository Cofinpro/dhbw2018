import java.util.Scanner;

public class Addierer {
    public Addierer(){
        double a;
        double b;

    }
    public double addierePrimitiv(double a, double b){
        return a+b;
    }

    public double addiereGekapselt(double a, double b){
        Double aDouble = new Double(a);
        Double bDouble = new Double(b);
        return aDouble + bDouble;
    }

    public double addiereRekursiv(double a, double b){
        if(b == 0)
            return a;
        if(b>0)
            return addiereRekursiv(a,b-1) + 1;
        throw new IllegalArgumentException("Keine Subtraktion möglich");
    }
}


/*

    Scanner scanner = new Scanner(System.in);
        System.out.println("Geben sie die erste Zahl ein: ");
                double a = scanner.nextDouble();
                System.out.println("Geben sie die zweite Zahl ein: ");
                double b = scanner.nextDouble(); */