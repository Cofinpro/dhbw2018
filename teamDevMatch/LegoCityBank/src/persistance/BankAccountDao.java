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

    public void writeBankAccountsToCSV(Set<Customer> customers) {
        CSVHelper helper = new CSVHelper("resources\\giroAccounts.csv");
        ArrayList<String> csvToStringsList = new ArrayList<>(); //ArrayList is used because its unclear how many BankAccounts exist in total

        for (Customer customer : customers) {
            for (BankAccount bankAccount : customer.getBankAccounts()) {
                csvToStringsList.add(customer.getUserName()+","+bankAccount.csvString()); //todo
            }
        }
        String[] csvToStrings = new String[csvToStringsList.size()];
        for (int i = 0; i<csvToStringsList.size(); i++) //ArrayList is converted to a String[]
            csvToStrings[i] = csvToStringsList.get(i);
        helper.writeCSV(csvToStrings);
    }

    public void readBankAccountsFromCSV(Set<Customer> customers) {
        CSVHelper helper = new CSVHelper("resources\\giroAccounts.csv");
        Collection<String[]> giroAccountRepresentations = helper.readCSV();
        for (String[] giroAccountRepresentation : giroAccountRepresentations) {
            String userName = giroAccountRepresentation[0];
            String accountType = giroAccountRepresentation[1];
            String accountNumber = giroAccountRepresentation[2];
            double balance = Double.parseDouble(giroAccountRepresentation[3]);
            String creationDate = giroAccountRepresentation[4];
            Customer customer = getCustomerByUserName(customers, userName);
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
        }
    }

    public Customer getCustomerByUserName(Set<Customer> customers, String userName) {
        for (Customer customer : customers) {
            if (customer.getUserName().equals(userName)) {
                return customer;
            }
        }
        throw new UserNotFoundException(userName);
    }
}

