package models;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    Customer customer = new Customer("testCustomer","passwort","Jonas","Trautmann");
    Set<BankAccount> bankAccountSet =new TreeSet<>();
    BankBook bankBook = new BankBook(customer);
    GiroAccount giroAccount = new GiroAccount(customer);
    MetalAccount metalAccount = new MetalAccount(customer);
    PremiumAccount premiumAccount = new PremiumAccount(customer);

    @Test
    void getCustomerNumber() {
        assertEquals(CustomerManager.getInstance().getNextCustomerNumber(),customer.getCustomerNumber());
    }

    @Test
    void getBankAccounts() {
        bankAccountSet.contains(bankBook);
        bankAccountSet.contains(giroAccount);
        bankAccountSet.contains(metalAccount);
        bankAccountSet.contains(premiumAccount);

        assertEquals(bankAccountSet,customer.getBankAccounts());
    }

    @Test
    void getTotalBalance() {
        double totalBalanceHelp =  bankBook.getBalance() + giroAccount.getBalance() + metalAccount.getBalance() + premiumAccount.getBalance();
        assertEquals(totalBalanceHelp,customer.getTotalBalance());
    }

    @Test
    void addBankAccountTest() {
        customer.addBankAccount(giroAccount);

        assertEquals(true,customer.getBankAccounts().contains(giroAccount));
    }

    @Test
    void deleteBankAccount() {
        customer.deleteBankAccount(metalAccount);

        assertEquals(false,customer.getBankAccounts().contains(metalAccount));
    }

}