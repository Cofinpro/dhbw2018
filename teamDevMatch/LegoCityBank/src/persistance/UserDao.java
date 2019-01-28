package persistance;

import exceptions.UserNotFoundException;
import helper.CSVHelper;
import models.BankAccount;
import models.Customer;
import models.GiroAccount;
import models.User;

import java.util.*;

public class UserDao {
    private static UserDao ourInstance = new UserDao();
    private BankAccount inspectedBankAccount;

    public static UserDao getInstance() {
        return ourInstance;
    }

    private Set<Customer> customers;
    private Customer loggedInCustomer;

    private UserDao() {
        customers = new TreeSet<>();
        readUsersFromCSV();
        readBankAccountsFromCSV();
    }

    private void readBankAccountsFromCSV() {
        readGiroAccountsFromCSV();
    }

    private void readGiroAccountsFromCSV() {
        CSVHelper helper = new CSVHelper("resources\\giroAccounts.csv");
        Collection<String[]> giroAccountRepresentations = helper.readCSV();
        for (String[] giroAccountRepresentation : giroAccountRepresentations) {
            String userName = giroAccountRepresentation[0];
            String accountNumber = giroAccountRepresentation[1];
            Customer customer = getCustomerByUserName(userName);
            GiroAccount giroAccount = new GiroAccount(accountNumber);
            if (customer == null) {
                throw new IllegalArgumentException("The csv is wrong. It accounts an account to an user who isn't a customer");
            }
            customer.addBankAccount(giroAccount);
        }
    }

    public Customer getCustomerByUserName(String userName) {
        for (Customer customer : customers) {
            if (customer.getUserName().equals(userName)) {
                return customer;
            }
        }
        throw new UserNotFoundException(userName);
    }

    public User getLoggedInUser() {
        return loggedInCustomer;
    }

    public void logUserIn(Customer customer) {
        if (!customers.contains(customer)) {
            throw new IllegalArgumentException();
        }
        loggedInCustomer = customer;
    }

    private void readUsersFromCSV() {
        readCustomersFromCSV();
    }

    private void readCustomersFromCSV() {
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
    }

    public void deleteBankAccount(Customer customer, BankAccount bankAccount) {
        Set<BankAccount> usersBankAccounts = customer.getBankAccounts();
        usersBankAccounts.remove(bankAccount);
    }

    public void setInspectedBankAccount(BankAccount bankAccount) {
        this.inspectedBankAccount = bankAccount;
    }

    public BankAccount getInspectedBankAccount() {
        return inspectedBankAccount;
    }
}
