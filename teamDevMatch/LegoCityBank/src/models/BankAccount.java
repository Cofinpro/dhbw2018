package models;

public abstract class BankAccount implements Comparable<BankAccount> {

    private String bankAccountNumber;
    private double balance; //de: Kontostand

    public void deposit(double depositAmount){
        this.balance += depositAmount;
    }
    public void disburse(double disburseAmount){
        this.balance -= disburseAmount;
    }

    public void processMonthlyInterest(){

    }
    public void processMonthlyFees(){

    }
    public String getBankAccountNumber(){
        return this.bankAccountNumber;
    }

    /**
     * gets monthly interest of the account type
     * @return monthly interest as decimal [double]
     */
    public abstract double getMonthlyInterest();
    /**
     * gets monthly fees of the account type expressed as a percentage
     * @return monthly interest as decimal [double]
     */
    public abstract double getMonthlyFeesPercentage();
    /**
     * gets monthly interest of the account type
     * @return monthly interest as decimal [double]
     */
    public abstract double getMonthlyFeesAbsolute();

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
        return 0;
    }
}
