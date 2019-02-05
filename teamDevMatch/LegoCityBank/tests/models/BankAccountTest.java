//##################################################################################
//Please note that this is a Junit 5 Jupiter test. Please add Junit 5.3 to classpath
//##################################################################################
package models;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BankAccountTest {

    private Customer customer = new Customer("Phape", "12345678", "Philipp",
            "Mayer", new BigInteger("7"));

    private GiroAccount account = new GiroAccount(customer);

    void setUp() {
        customer.addBankAccount(account);
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
    void processMonthlyInterest() {
    }

    @Test
    void processMonthlyFees() {
    }

    @Test
    void getBankAccountNumber() {
    }

    @Test
    void getBalance() {
    }

    @Test
    void getCreationDate() {
    }

    @Test
    void compareTo() {
    }

    @Test
    void isDeletable() {
    }
}