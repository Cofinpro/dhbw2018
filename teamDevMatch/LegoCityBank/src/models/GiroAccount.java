package models;

public class GiroAccount extends BankAccount {

    private static final double monthlyInterest = 0.0; //in decimal
    private static final double monthlyFeesPercentage = 0.0; //in decimal
    private static final double monthlyFeesAbsolute = 5.0; //in €

    public GiroAccount(String bankAccountNumber) {
        super(bankAccountNumber, "GiroAccount");
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
