package persistance;

import exceptions.UserNotFoundException;
import helper.CSVHelper;
import models.*;

import java.util.*;

public class UserDao {
    private static UserDao instance;

    public static UserDao getInstance() {
        if (instance == null)
            instance = new UserDao();
        return instance;
    }

    private UserDao() {}

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

    public Set<Customer> readCustomersFromCSV() {
        Set<Customer> customers = new TreeSet<>();
        CSVHelper helper = new CSVHelper("resources\\customers.csv");
        Collection<String[]> customerRepresentations = helper.readCSV();

        for (String[] customerRepresentation : customerRepresentations) {
            String username = customerRepresentation[0];
            String password = customerRepresentation[1];
            String firstName = customerRepresentation[2];
            String lastName = customerRepresentation[3];
            String customerNumber = customerRepresentation[4];
            Customer customer = new Customer(username, password, firstName, lastName, customerNumber);
            customers.add(customer);
        }
        return customers;
    }

    public void writeCustomersToCSV(Set<Customer> customers) {
        CSVHelper helper = new CSVHelper("resources\\customers.csv");
        String[] csvToStrings = new String[customers.size()];
        int i = 0;
        for (Customer customer : customers) {
            csvToStrings[i] = customer.csvString();
            i++;
        }
        helper.writeCSV(csvToStrings);
    }
}
