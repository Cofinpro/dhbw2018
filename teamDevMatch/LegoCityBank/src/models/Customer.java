package models;

import java.util.*;

public class Customer extends User implements csvModel{

    private String customerNumber;
    private Set<BankAccount> bankAccountSet= new TreeSet<>();

    public Customer(String userName, String password, String firstName, String lastName) {
        super(userName, password, firstName, lastName);

        String error;
        if (!(error = isUserNameValid(userName)).equals(""))
            throw new IllegalArgumentException(error);
        if (!(error = isPasswordValid(password)).equals(""))
            throw new IllegalArgumentException(error);
        if (!(error = isFirstNameValid(firstName)).equals(""))
            throw new IllegalArgumentException(error);
        if (!(error = isLastNameValid(lastName)).equals(""))
            throw new IllegalArgumentException(error);

        this.customerNumber = CustomerManager.getInstance().getNextCustomerNumber();
    }

    public Customer(String userName, String password, String firstName, String lastName, String customerNumber) {
        super(userName, password, firstName, lastName);
        this.customerNumber = customerNumber;
    }

    public static String isUserNameValid (String userNameToCheck) {
        if (userNameToCheck == null || userNameToCheck.length() < 4 || userNameToCheck.length() > 16)
            return "Nutzername sollte zwischen 4 und 16 Zeichen haben";
        if (CustomerManager.getInstance().isUserNameTaken(userNameToCheck))
            return "Nutzername bereits vergeben";
        return "";
    }

    public static String isPasswordValid (String passwordToCheck) {
        if (passwordToCheck == null || passwordToCheck.length() < 5 || passwordToCheck.length() > 32)
            return "Passwort sollte zwischen 5 und 32 Zeichen haben";
        return "";
    }

    public static String isFirstNameValid(String firstNameToCheck) {
        if (firstNameToCheck == null || firstNameToCheck.length() < 2 || firstNameToCheck.length() > 16)
            return "Vorname sollte zwischen 2 und 16 Zeichen haben";
        return "";
    }

    public static String isLastNameValid (String lastNameToCheck) {
        if (lastNameToCheck == null || lastNameToCheck.length() < 4 || lastNameToCheck.length() > 16)
            return "Nachname sollte zwischen 2 und 16 Zeichen haben";
        return "";
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
    public String makeCSVString() {
        return getClass().getSimpleName()+","+super.makeCSVString()+","+customerNumber;
    }
}
