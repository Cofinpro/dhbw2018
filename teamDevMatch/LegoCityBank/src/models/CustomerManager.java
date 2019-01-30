package models;

import persistance.BankAccountDao;
import persistance.UserDao;
import java.util.Set;

public class CustomerManager {
    private static CustomerManager instance;
    private Set<Customer> customers;
    private Customer loggedInCustomer;
    private BankAccount inspectedBankAccount;

    private CustomerManager(){
        customers = UserDao.getInstance().readCustomersFromCSV();
        BankAccountDao.getInstance().readBankAccountsFromCSV(customers);
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
        return BankAccountDao.getInstance().getCustomerByUserName(customers, userName);
    }

    public String getNextBankAccountNumber() {
        long highestBankAccountNumber = 0;
        for (Customer customer : customers) {
            for (BankAccount bankAccount : customer.getBankAccounts()) {
                long banLong = Long.parseLong(bankAccount.getBankAccountNumber());
                if (banLong > highestBankAccountNumber)
                    highestBankAccountNumber = banLong;
            }
        }
        return String.valueOf(highestBankAccountNumber+1);
    }

    public String getNextCustomerNumber() {
        long highestCustomerNumber = 0;
        for (Customer customer : customers) {
            long cnLong = Long.parseLong(customer.getCustomerNumber());
            if (cnLong > highestCustomerNumber)
                highestCustomerNumber = cnLong;
        }
        return String.valueOf(highestCustomerNumber+1);
    }

    /**
     * Writes Customers AND their Accounts to the corresponding CSV
     */
    public void saveCustomersToCSV() {
        UserDao.getInstance().writeCustomersToCSV(customers);
        BankAccountDao.getInstance().writeBankAccountsToCSV(customers);
    }

    public static CustomerManager getInstance() {
        if(instance == null)
            instance = new CustomerManager();
        return instance;
    }
}
