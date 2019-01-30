package persistance;

import exceptions.UserNotFoundException;
import helper.CSVHelper;
import models.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class BankAccountDao {
    private static BankAccountDao instance;

    public static BankAccountDao getInstance() {
        if (instance == null)
            instance = new BankAccountDao();
        return instance;
    }

    private BankAccountDao() {}

    public void writeBankAccountsToCSV(Set<User> users) {
        CSVHelper helper = new CSVHelper("resources\\giroAccounts.csv");
        ArrayList<String> csvToStringsList = new ArrayList<>(); //ArrayList is used because its unclear how many BankAccounts exist in total

        Customer customer;
        for (User user : users) {
            if (user instanceof Customer) {
                customer = (Customer)user;
                for (BankAccount bankAccount : customer.getBankAccounts()) {
                    csvToStringsList.add(customer.getUserName()+","+bankAccount.csvString());
                }
            }
        }
        String[] csvToStrings = new String[csvToStringsList.size()];
        for (int i = 0; i<csvToStringsList.size(); i++) //ArrayList is converted to a String[]
            csvToStrings[i] = csvToStringsList.get(i);
        helper.writeCSV(csvToStrings);
    }

    public void readBankAccountsFromCSV(Set<User> users) {
        CSVHelper helper = new CSVHelper("resources\\giroAccounts.csv");
        Collection<String[]> giroAccountRepresentations = helper.readCSV();
        for (String[] giroAccountRepresentation : giroAccountRepresentations) {
            String userType = giroAccountRepresentation[0];

            switch (userType) {
                case "Admin":
                    break;
                case "Customer":
                    String userName = giroAccountRepresentation[1];
                    String accountType = giroAccountRepresentation[2];
                    String accountNumber = giroAccountRepresentation[3];
                    double balance = Double.parseDouble(giroAccountRepresentation[4]);
                    String creationDate = giroAccountRepresentation[5];

                    Customer customer = (Customer)getUserByUserName(users, userName);

                    if (customer == null) {
                        throw new IllegalArgumentException();
                    }
                    switch (accountType) {
                        case "BankBook":
                            BankBook bankBook = new BankBook(accountNumber, balance, creationDate);
                            customer.addBankAccount(bankBook);
                            break;
                        case "GiroAccount":
                            GiroAccount giroAccount = new GiroAccount(accountNumber, balance, creationDate);
                            customer.addBankAccount(giroAccount);
                            break;
                        case "PremiumAccount":
                            PremiumAccount premiumAccount = new PremiumAccount(accountNumber, balance, creationDate);
                            customer.addBankAccount(premiumAccount);
                            break;
                        case "MetalAccount":
                            MetalAccount metalAccount = new MetalAccount(accountNumber, balance, creationDate);
                            customer.addBankAccount(metalAccount);
                            break;
                        default:
                            break;
                    }
                    break;
            }
        }
    }

    public User getUserByUserName(Set<User> users, String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        throw new UserNotFoundException(userName);
    }
}

