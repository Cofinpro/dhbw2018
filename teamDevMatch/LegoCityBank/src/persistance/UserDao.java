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
        readGiroAccountsFromCSV(customers);
    }

    private void readGiroAccountsFromCSV(Set<Customer> customers) {
        CSVHelper helper = new CSVHelper("resources\\giroAccounts.csv");
        Collection<String[]> giroAccountRepresentations = helper.readCSV();
        for (String[] giroAccountRepresentation : giroAccountRepresentations) {
            String userName = giroAccountRepresentation[0];
            String accountNumber = giroAccountRepresentation[1];
            Customer customer = getCustomerByUserName(customers, userName);
            GiroAccount giroAccount = new GiroAccount(accountNumber);
            if (customer == null) {
                throw new IllegalArgumentException("The csv is wrong. It accounts an account to an user who isn't a customer");
            }
            customer.addBankAccount(giroAccount);
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

    public Set<Customer> readCustomersFromCSV() {
        Set<Customer> customers = new TreeSet<>();
        CSVHelper helper = new CSVHelper("resources\\customers.csv");
        Collection<String[]> customerRepresentations = helper.readCSV();
        for (String[] customerRepresentation : customerRepresentations) {
            String username = customerRepresentation[0];
            String firstName = customerRepresentation[1];
            String lastName = customerRepresentation[2];
            String password = customerRepresentation[3];
            String customerNumber = customerRepresentation[4];
            Customer customer = new Customer(username, password, firstName, lastName, customerNumber);
            customers.add(customer);
        }
        return customers;
    }

    public void writeCustomersToCSV(Set<Customer> customers) {
        CSVHelper helper = new CSVHelper("resources\\example.csv");
        String[] csvToStrings = new String[customers.size()];
        int i = 0;
        for (Customer customer : customers) {
            csvToStrings[i] = customer.csvString();
            i++;
        }
        helper.writeCustomersToCSV(csvToStrings);
    }

    public void deleteBankAccount(Customer customer, BankAccount bankAccount) {
        Set<BankAccount> usersBankAccounts = customer.getBankAccounts();
        usersBankAccounts.remove(bankAccount);
    }


}
