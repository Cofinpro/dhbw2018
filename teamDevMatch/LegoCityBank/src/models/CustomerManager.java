package models;

import persistance.BankAccountDao;
import persistance.UserDao;

import java.math.BigInteger;
import java.util.Set;
import java.util.TreeSet;

public class CustomerManager {
    private static CustomerManager instance;
    private Set<User> users;
    private User loggedInUser;
    private BankAccount inspectedBankAccount;

    private CustomerManager(){
        users = UserDao.getInstance().readUsersFromCSV();
        BankAccountDao.getInstance().readBankAccountsFromCSV(users);
    }

    public boolean addCustomer(Customer customer) {
       return users.add(customer);
    }

    public void removeCustomer(Customer customer) {
        users.remove(customer);
    }

    public void logUserIn(User user) {
        if (!users.contains(user)) {
            throw new IllegalArgumentException();
        }
        loggedInUser = user;
    }

    public void logUserOut() {
        loggedInUser = null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setInspectedBankAccount(BankAccount bankAccount) {
        this.inspectedBankAccount = bankAccount;
    }

    public BankAccount getInspectedBankAccount() {
        return inspectedBankAccount;
    }

    public User getUserByUserName(String userName) {
        return BankAccountDao.getInstance().getUserByUserName(users, userName);
    }

    public Set<BankAccount> getAllBankAccounts() {
        Customer customer;
        Set<BankAccount> allBankAccounts = new TreeSet<BankAccount>();
        for (User user : users) {
            if (user instanceof Customer) {
                customer = (Customer)user;
                allBankAccounts.addAll(customer.getBankAccounts());
            }
        }
        return  allBankAccounts;
    }

    public BigInteger getNextBankAccountNumber() {
        Customer customer;
        BigInteger highestBankAccountNumber = new BigInteger("0");
        for (User user : users) {
            if (user instanceof Customer) {
                customer = (Customer)user;
                for (BankAccount bankAccount : customer.getBankAccounts()) {
                    if (bankAccount.getBankAccountNumber().compareTo(highestBankAccountNumber) > 0)
                        highestBankAccountNumber = bankAccount.getBankAccountNumber();
                }
            }
        }
        return highestBankAccountNumber.add(new BigInteger("1"));
    }

    public BigInteger getNextCustomerNumber() {
        Customer customer;
        BigInteger highestCustomerNumber = new BigInteger("0");
        for (User user : users) {
            if (user instanceof Customer) {
                customer = (Customer)user;
                if (customer.getCustomerNumber().compareTo(highestCustomerNumber) > 0)
                    highestCustomerNumber = customer.getCustomerNumber();
            }
        }
        return highestCustomerNumber.add(new BigInteger("1"));
    }

    public boolean isUserNameTaken(String userNameToCheck) {
        for (User user : users) {
            if (userNameToCheck.equals(user.getUserName()))
                return true;
        }
        return false;
    }
    /**
     * Writes Customers AND their Accounts to the corresponding CSV
     */
    public void saveCustomersToCSV() {
        UserDao.getInstance().writeUsersToCSV(users);
        BankAccountDao.getInstance().writeBankAccountsToCSV(users);
    }

    public static CustomerManager getInstance() {
        if(instance == null)
            instance = new CustomerManager();
        return instance;
    }

    public void removeBankAccount() {
        // TODO: 31.01.2019  
    }
}
