package models;

import java.math.BigInteger;

public class PremiumAccount extends BankAccount {

    private static final String ACCOUNT_TYPE = "Premium Konto";
    private static final double MONTHLY_INTEREST = 0.0;
    private static final double MONTHLY_FEES_PERCENTAGE = 0.005;
    private static final double MONTHLY_FEES_ABSOLUTE = 0.0;

    public PremiumAccount(Customer customer) {
        super(customer);
    }

    public PremiumAccount(Customer owner, BigInteger bankAccountNumber, double balance, String creationDate) {
        super (owner, bankAccountNumber, balance, creationDate);
    }

    public String getAccountType() {
        return ACCOUNT_TYPE;
    }

    @Override
    public double getMonthlyInterest() {
        return MONTHLY_INTEREST;
    }

    @Override
    public double getMonthlyFeesPercentage() {
        return MONTHLY_FEES_PERCENTAGE;
    }

    @Override
    public double getMonthlyFeesAbsolute() {
        return MONTHLY_FEES_ABSOLUTE;
    }

    @Override
    public String makeCSVString() {
        return getOwner().getUserName()+","+getClass().getSimpleName()+","+getBankAccountNumber()+","+getBalance()+","+getCreationDate();
    }
}
