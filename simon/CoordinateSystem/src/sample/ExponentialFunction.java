package sample;

public class ExponentialFunction extends Function {

    private String name;
    private double a;
    private double b;

    public ExponentialFunction(String name, double a, double b) {
        super(name);
        if (b < 0) {
            throw new IllegalArgumentException("b < 0 is not allowed");
        }
        this.a = a;
        this.b = b;
    }

    @Override
    public double getY(double x) {
        return a * Math.pow(b, x);
    }
}
