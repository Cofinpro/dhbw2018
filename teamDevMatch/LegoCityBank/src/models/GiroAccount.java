package models;

public class GiroAccount extends BankAccount {

    private static final double monthlyInterest = 0.0; //in decimal
    private static final double monthlyFeesPercentage = 0.0; //in decimal
    private static final double monthlyFeesAbsolute = 5.0; //in €


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
