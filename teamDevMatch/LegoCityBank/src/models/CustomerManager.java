package models;

import persistance.UserDao;
import java.util.Set;

public class CustomerManager {
    private static CustomerManager instance;
    private Set<Customer> customers;
    private Customer loggedInCustomer;
    private BankAccount inspectedBankAccount;

    private CustomerManager(){
        customers = UserDao.getInstance().readCustomersFromCSV();
        UserDao.getInstance().readBankAccountsFromCSV(customers);
    }

    public boolean addCustomer(Customer customer) {
       return customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    public void logUserIn(Customer customer) {
        if (!customers.contains(customer)) {
            throw new IllegalArgumentException();
        }
        loggedInCustomer = customer;
    }

    public void logUserOut() {
        loggedInCustomer = null;
    }

    public Customer getLoggedInCustomer() {
        return loggedInCustomer;
    }

    public void setInspectedBankAccount(BankAccount bankAccount) {
        this.inspectedBankAccount = bankAccount;
    }

    public BankAccount getInspectedBankAccount() {
        return inspectedBankAccount;
    }

    public Customer getCustomerByUserName(String userName) {
        return UserDao.getInstance().getCustomerByUserName(customers, userName);
    }

    /**
     * Writes Customers AND their Accounts to the corresponding CSV
     */
    public void saveCustomersToCSV() {
        UserDao userDao = UserDao.getInstance();

        userDao.writeCustomersToCSV(customers);
        userDao.writeBankAccountsToCSV(customers);
    }

    public static CustomerManager getInstance() {
        if(instance == null)
            instance = new CustomerManager();
        return instance;
    }
}
