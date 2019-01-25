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

    public void processMonthlyInterest(int months){
        for (int m = 0; m < months; m++){
            balance += balance*this.getMonthlyInterest();
        }
    }
    public void processMonthlyFees(int months){
        for (int m = 0; m < months; m++){
            balance += balance*this.getMonthlyFeesPercentage();
            balance += this.getMonthlyFeesAbsolute();
        }
    }
    public String getBankAccountNumber(){
        return this.bankAccountNumber;
    }

    public double getBalance() {
        return this.balance;
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
     * gets monthly interest of the account type expressed absolutely
     * @return monthly interest as decimal [double] in €
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
