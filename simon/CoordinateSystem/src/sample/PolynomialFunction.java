package sample;

public class PolynomialFunction extends Function {
    private String name;
    private double[] bases;

    public PolynomialFunction(String name, double... bases) {
        super(name);
        if (bases.length < 1) {
            new IllegalArgumentException("Polynomial Function may only be initialized with multiple bases.");
        }
        if (bases[0] != 0) {
            new IllegalArgumentException("The first base of a polynomial function cannot be 0 as it doesn't make sense to include this base then.");
        }
        this.bases = bases;
    }

    @Override
    public double getY(double x) {
        double sum = 0;
        for (int i = 0; i < bases.length; i++) {
            sum += bases[i] * Math.pow(x, bases.length-i-1);
        }
        return sum;
    }
}
