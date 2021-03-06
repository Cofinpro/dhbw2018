package models;

import java.math.BigInteger;
import java.util.Calendar;

public abstract class BankAccount implements Comparable<BankAccount>, csvModel {

    private Customer owner;
    private BigInteger bankAccountNumber;
    private double balance;
    private String creationDate;

    /**
     * Use when user creates a new BankAccount
     */
    public BankAccount(Customer customer) {
        this.owner = customer;
        this.bankAccountNumber = CustomerManager.getInstance().getNextBankAccountNumber();
        Calendar calendar = Calendar.getInstance();
        this.creationDate = calendar.get(Calendar.DAY_OF_MONTH) + "." + (calendar.get(Calendar.MONTH)+1) +
                "." + calendar.get(Calendar.YEAR);
    }

    /**
     * Use to construct an already existing BA (eg. when reading from CSV)
     * @param owner Username of account owner
     * @param bankAccountNumber Number of the BankAccount
     * @param creationDate date of initial creation
     */
    public BankAccount(Customer owner, BigInteger bankAccountNumber, double balance, String creationDate) {
        this.owner = owner;
        this.bankAccountNumber = bankAccountNumber;
        this.balance = balance;
        this.creationDate = creationDate;
    }

    public BankAccount(Customer owner, BigInteger bankAccountNumber, String creationDate){
        this.owner = owner;
        this.bankAccountNumber = bankAccountNumber;
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

    public BigInteger getBankAccountNumber(){
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
        return this.bankAccountNumber.compareTo(otherBankAccount.bankAccountNumber);
    }

    public boolean isDeletable() {
        return balance < 0.01;
    }

    public Customer getOwner() {
        return owner;
    }

    public String toString() {
        return this.getClass().getSimpleName();

    }
}
