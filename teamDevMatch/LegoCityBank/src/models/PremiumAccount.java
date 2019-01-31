package models;

public class PremiumAccount extends BankAccount {

    private static final String accountType = "Premium Konto";
    private static final double monthlyInterest = 0.0; //in decimal
    private static final double monthlyFeesPercentage = 0.005; //in decimal
    private static final double monthlyFeesAbsolute = 0.0; //in €

    public PremiumAccount() {
        super();
    }

    public PremiumAccount(Customer owner, String bankAccountNumber, double balance, String creationDate) {
        super (owner, bankAccountNumber, balance, creationDate);
    }

    public String getAccountType() {
        return accountType;
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

    @Override
    public String csvString() {
        return getOwner().getUserName()+","+getClass().getSimpleName()+","+getBankAccountNumber()+","+getBalance()+","+getCreationDate();
    }
}
