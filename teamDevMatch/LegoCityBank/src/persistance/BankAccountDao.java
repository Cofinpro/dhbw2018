package persistance;

import exceptions.UserNotFoundException;
import helper.CSVHelper;
import models.*;

import java.math.BigInteger;
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

    /**
     * iterates over all users, checks for their bank accounts and creates a String[] that is given to the csvHelper
     * @see helper.CSVHelper
     * @param users set of all existing users
     */
    public void writeBankAccountsToCSV(Set<User> users) {
        CSVHelper helper = new CSVHelper("resources\\bankAccounts.csv");
        ArrayList<String> csvToStringsList = new ArrayList<>(); //ArrayList is used because its unclear how many BankAccounts exist in total

        Customer customer;
        for (User user : users) {
            if (user instanceof Customer) {
                customer = (Customer)user;
                for (BankAccount bankAccount : customer.getBankAccounts()) {
                    csvToStringsList.add(bankAccount.makeCSVString());
                }
            }
        }
        String[] csvToStrings = new String[csvToStringsList.size()];
        for (int i = 0; i<csvToStringsList.size(); i++) //ArrayList is converted to a String[]
            csvToStrings[i] = csvToStringsList.get(i);
        helper.writeCSV(csvToStrings);
    }

    public void readBankAccountsFromCSV(Set<User> users) {
        CSVHelper helper = new CSVHelper("resources\\bankAccounts.csv");
        Collection<String[]> giroAccountRepresentations = helper.readCSV();
        @SuppressWarnings("Duplicates") //similar code in UserDao
        int userNameIndex = 0;
        int accountTypeIndex = 1;
        int accountNumberIndex = 2;
        int currencyAmountIndex = 3;
        int creationDateIndex = 4;
        for (String[] giroAccountRepresentation : giroAccountRepresentations) {
            String userName = giroAccountRepresentation[userNameIndex];
            String accountType = giroAccountRepresentation[accountTypeIndex];
            BigInteger accountNumber = new BigInteger(giroAccountRepresentation[accountNumberIndex]);
            double currencyAmount = Double.parseDouble(giroAccountRepresentation[currencyAmountIndex]);
            String creationDate = giroAccountRepresentation[creationDateIndex];

            Customer customer = (Customer)getUserByUserName(users, userName);
            if (customer == null) {
                throw new IllegalArgumentException();
            }

            createBankAccount(accountType, customer, accountNumber, currencyAmount, creationDate);
        }
    }

    private void createBankAccount(String accountType, Customer customer, BigInteger accountNumber,
                                   double currencyAmount, String creationDate) {
        switch (accountType) {
            case "BankBook":
                BankBook bankBook = new BankBook(customer, accountNumber, currencyAmount, creationDate);
                customer.addBankAccount(bankBook);
                break;
            case "GiroAccount":
                GiroAccount giroAccount = new GiroAccount(customer, accountNumber, currencyAmount, creationDate);
                customer.addBankAccount(giroAccount);
                break;
            case "PremiumAccount":
                PremiumAccount premiumAccount = new PremiumAccount(customer, accountNumber, currencyAmount, creationDate);
                customer.addBankAccount(premiumAccount);
                break;
            case "MetalAccount":
                MetalAccount metalAccount = new MetalAccount(customer, accountNumber, currencyAmount, creationDate);
                customer.addBankAccount(metalAccount);
                break;
            default:
                break;
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

