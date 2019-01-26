package models;

public class MetalAccount extends BankAccount{
    private static final double monthlyInterest = 0.0; //in decimal
    private static final double monthlyFeesPercentage = 0.005; //in decimal
    private static final double monthlyFeesAbsolute = 0.0; //in €

    public MetalAccount(String bankAccountNumber) {
        super(bankAccountNumber);
    }

    public static String getAccountType() {
        return "MetalAccount";
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
