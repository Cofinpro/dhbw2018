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

        if(this.bankAccountNumber.length() > otherBankAccount.bankAccountNumber.length())
            return 1;
        if(this.bankAccountNumber.length() < otherBankAccount.bankAccountNumber.length())
            return -1;
        //if the numbers have the same amount of digits:
        else {
            for (int i = 0; i < this.bankAccountNumber.length(); i++) {
                if (this.bankAccountNumber.charAt(i) > otherBankAccount.bankAccountNumber.charAt(i))
                    return 1;
                if (this.bankAccountNumber.charAt(i) < otherBankAccount.bankAccountNumber.charAt(i))
                    return -1;
            }
        }
    }
}
