package models;

import java.util.*;

public class Customer extends User {

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


    public void addBankAccount(BankAccount newBankAccount) {
        bankAccountSet.add(newBankAccount);
    }

    public void deleteBankAccount(BankAccount bankAccount) {
        bankAccountSet.remove(bankAccount);
    }
}
