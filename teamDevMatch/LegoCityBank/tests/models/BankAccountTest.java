package models;

import org.junit.Test;

import static org.junit.Assert.*;

class BankAccountTest {

    Customer customer = new Customer("Phape", "12345678", "Philipp",
            "Mayer", "7");
    GiroAccount account = new GiroAccount("1043141");

    void setUp() {
        customer.addBankAccount(account);
    }
    @Test
    void testDeposit() {
        account.deposit(500.0);

        assertEquals(500.0, account.getBalance());
    }

    @Test
    void testDisburse() {
        account.disburse(250.0);

        assertEquals(-250.0, account.getBalance());
    }

    @Test
    void testProcessMonthlyInterest() {

    }

    @Test
    void testProcessMonthlyFees() {
    }

    @Test
    void testGetBankAccountNumber() {
    }

    @Test
    void testCompareTo() {
    }
}