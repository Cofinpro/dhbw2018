package models;

public class BankBook extends BankAccount {

    private static final double monthlyInterest = 0.01; //in decimal
    private static final double monthlyFeesPercentage = 0.0; //in decimal
    private static final double monthlyFees = 0.0; //in €

    @Override
    public double getMonthlyInterest() {
        return 0;
    }

    @Override
    public double getMonthlyFeesPercentage() {
        return 0;
    }

    @Override
    public double getMonthlyFeesAbsolute() {
        return 0;
    }
}
