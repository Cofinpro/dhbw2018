package daos;

import exceptions.UserNotFoundException;
import helper.CSVHelper;
import models.BankAccount;
import models.Customer;
import models.GiroAccount;
import models.User;
import views.CustomMain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UserDao {
    private static UserDao ourInstance = new UserDao();
    private BankAccount inspectedBankAccount;

    public static UserDao getInstance() {
        return ourInstance;
    }

    private List<User> users;
    private User loggedInUser;

    private UserDao() {
        users = new ArrayList<>();
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
            User user = getUserByUserName(userName);
            GiroAccount giroAccount = new GiroAccount(accountNumber);
            Customer customer = (Customer) user;
            if (customer == null) {
                throw new IllegalArgumentException("The csv is wrong. It accounts an account to an user who isn't a customer");
            }
            customer.addBankAccount(giroAccount);
        }
    }

    public User getUserByUserName(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        throw new UserNotFoundException(userName);
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void logUserIn(User user) {
        if (!users.contains(user)) {
            throw new IllegalArgumentException();
        }
        loggedInUser = user;
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
            User user = new Customer(username, password, firstName, lastName, customerNumber);
            users.add(user);
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
