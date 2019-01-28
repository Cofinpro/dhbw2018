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

    public void logUserIn(Customer customer) {
        if (!customers.contains(customer)) {
            throw new IllegalArgumentException();
        }
        loggedInCustomer = customer;
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

    public static CustomerManager getInstance() {
        if(instance == null)
            instance = new CustomerManager();
        return instance;
    }
}
