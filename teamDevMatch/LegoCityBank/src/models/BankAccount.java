package models;

import java.util.Calendar;

public abstract class BankAccount implements Comparable<BankAccount>, csvModel {

    private String bankAccountNumber;
    private double balance; //de: Kontostand
    private String creationDate;

    /**
     * Use when user creates a new BankAccount
     */
    public BankAccount() {
        //todo: add random bankaccountNumber
        this.bankAccountNumber = "placeholder";
        Calendar calendar = Calendar.getInstance();
        this.creationDate = calendar.get(Calendar.DAY_OF_MONTH) + "." + (calendar.get(Calendar.MONTH)+1) +
                "." + calendar.get(Calendar.YEAR);
    }

    /**
     * Use to construct an already existing BA (eg. when reading from CSV)
     * @param bankAccountNumber Number of the BankAccount
     * @param creationDate date of initial creation
     */
    public BankAccount(String bankAccountNumber, double balance, String creationDate) {
        this.bankAccountNumber = bankAccountNumber;
        this.balance = balance;
        this.creationDate = creationDate;
    }

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

    public abstract String getAccountType();

    public double getBalance() {
        return this.balance;
    }

    public String getCreationDate() {
        return this.creationDate;
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

    public boolean isDeletable() {
        return balance == 0;
    }
}
