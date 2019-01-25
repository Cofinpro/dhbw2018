package models;

import java.util.Set;

public class Customer extends User {

    public Set<BankAccount> getBankAccounts() {
        return null;
    }

    public String getBankAccountNumber() {
        return null;
    }

    public void addBankAccount(BankAccount newBankAccount) {

    }

    public void deleteBankAccount(BankAccount bankAccount) {}
}
