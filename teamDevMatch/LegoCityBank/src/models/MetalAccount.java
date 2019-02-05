package models;

public class MetalAccount extends BankAccount{

    private static final String ACCOUNT_TYPE = "Metall Konto";
    private static final double MONTHLY_INTEREST = 0.0;
    private static final double MONTHLY_FEES_PERCENTAGE = 0.0;
    private static final double MONTHLY_FEES_ABSOLUTE = 100.0;
    private double goldAmountInGram;
    private GoldPrice goldPrice = GoldPrice.getInstance();

    public MetalAccount(Customer customer) {
        super(customer);
    }

    public MetalAccount(Customer owner, String bankAccountNumber, double goldAmountInGram, String creationDate) {
        super (owner, bankAccountNumber, creationDate);
        this.goldAmountInGram = goldAmountInGram;
    }

    public String getAccountType() {
        return ACCOUNT_TYPE;
    }

    public double getGoldAmountInGram() {
        return goldAmountInGram;
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
    public double getBalance() {
        return goldPrice.calculateWorth(goldAmountInGram);
    }

    @Override
    public void deposit(double depositAmount){
        this.goldAmountInGram += depositAmount/goldPrice.getDollarPerGramOfGold();
    }

    @Override
    public void disburse(double disburseAmount) {
        this.goldAmountInGram -= disburseAmount/goldPrice.getDollarPerGramOfGold();
    }

    @Override
    public String makeCSVString() {
        return getOwner().getUserName()+","+getClass().getSimpleName()+","+getBankAccountNumber()+","+goldAmountInGram+","+getCreationDate();
    }
}
