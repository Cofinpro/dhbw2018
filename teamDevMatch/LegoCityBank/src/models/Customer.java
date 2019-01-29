package models;

import java.util.*;

public class Customer extends User implements csvModel{

    private String customerNumber;
    private Set<BankAccount> bankAccountSet= new TreeSet<>();

    public Customer(String userName, String password, String firstName, String lastName, String customerNumber) {
        super(userName, password, firstName, lastName);
        this.customerNumber = customerNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public Set<BankAccount> getBankAccounts() {
        return bankAccountSet;
    }

    public double getTotalBalance(){
        double totalBalance = 0;
        for (BankAccount bankAccount : bankAccountSet) {
            totalBalance += bankAccount.getBalance();
        }
        return totalBalance;
    }

    public void addBankAccount(BankAccount newBankAccount) {
        bankAccountSet.add(newBankAccount);
    }

    public void deleteBankAccount(BankAccount bankAccount) {
        bankAccountSet.remove(bankAccount);
    }

    @Override
    public String csvString() {
        return super.csvString()+","+customerNumber;
    }
}
