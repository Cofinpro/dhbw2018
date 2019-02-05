package models;

public class BankBook extends BankAccount {

    private static final String ACCOUNT_TYPE = "Sparbuch";
    private static final double MONTHLY_INTEREST = 0.01; //in decimal
    private static final double MONTHLY_FEES_PERCENTAGE = 0.0; //in decimal
    private static final double MONTHLY_FEES_ABSOLUTE = 0.0; //in €

    public BankBook(Customer customer) {
        super(customer);
    }

    public BankBook(Customer owner, String bankAccountNumber, double balance, String creationDate) {
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
