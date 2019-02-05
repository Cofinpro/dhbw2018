package models;

import java.math.BigInteger;

public class GiroAccount extends BankAccount {

    private static final String ACCOUNT_TYPE = "Giro Konto";
    private static final double MONTHLY_INTEREST = 0.0;
    private static final double MONTHLY_FEES_PERCENTAGE = 0.0;
    private static final double MONTHLY_FEES_ABSOLUTE = 5.0;

    public GiroAccount(Customer customer) {
        super(customer);
    }

    public GiroAccount(Customer owner, BigInteger bankAccountNumber, double balance, String creationDate) {
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
