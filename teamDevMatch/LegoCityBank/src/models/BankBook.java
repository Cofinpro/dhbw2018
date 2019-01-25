package models;

public class BankBook extends BankAccount {

    private static final double monthlyInterest = 0.01; //in decimal
    private static final double monthlyFeesPercentage = 0.0; //in decimal
    private static final double monthlyFeesAbsolute = 0.0; //in €

    public BankBook(String bankAccountNumber) {
        super(bankAccountNumber);
    }

    @Override
    public double getMonthlyInterest() {
        return monthlyInterest;
    }

    @Override
    public double getMonthlyFeesPercentage() {
        return monthlyFeesPercentage;
    }

    @Override
    public double getMonthlyFeesAbsolute() {
        return monthlyFeesAbsolute;
    }
}
