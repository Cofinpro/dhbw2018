package models;

import persistance.BankAccountDao;
import persistance.UserDao;
import java.util.Set;

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

    public User getCustomerByUserName(String userName) {
        return BankAccountDao.getInstance().getUserByUserName(users, userName);
    }

    public String getNextBankAccountNumber() {
        Customer customer;
        long highestBankAccountNumber = 0;
        for (User user : users) {
            if (user instanceof Customer) {
                customer = (Customer)user;
                for (BankAccount bankAccount : customer.getBankAccounts()) {
                    long banLong = Long.parseLong(bankAccount.getBankAccountNumber());
                    if (banLong > highestBankAccountNumber)
                        highestBankAccountNumber = banLong;
                }
            }
        }
        return String.valueOf(highestBankAccountNumber+1);
    }

    public String getNextCustomerNumber() {
        Customer customer;
        long highestCustomerNumber = 0;
        for (User user : users) {
            if (user instanceof Customer) {
                customer = (Customer)user;
                long cnLong = Long.parseLong(customer.getCustomerNumber());
                if (cnLong > highestCustomerNumber)
                    highestCustomerNumber = cnLong;
            }
        }
        return String.valueOf(highestCustomerNumber+1);
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
}
