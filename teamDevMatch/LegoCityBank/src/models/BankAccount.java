package models;

public abstract class BankAccount implements Comparable<BankAccount> {

    protected String bankAccountNumber;
    protected double balance; //de: Kontostand

    public void deposit(){

    }
    public void disburse(){

    }
    public abstract void processMonthlyInterest();
    public abstract void processMonthlyFees();
    public abstract String getBankAccountNumber();

    @Override
    public int compareTo(BankAccount otherBankAccount) {
        if(this.bankAccountNumber > otherBankAccount.bankAccountNumber)
            return 1;
        if(this.bankAccountNumber < otherBankAccount.bankAccountNumber)
            return 0;
    }
}
