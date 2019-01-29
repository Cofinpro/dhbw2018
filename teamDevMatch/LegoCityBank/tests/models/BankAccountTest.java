//##################################################################################
//Please note that this is a Junit 5 Jupiter test. Please add Junit 5.3 to classpath
//##################################################################################
package models;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BankAccountTest {

    Customer customer = new Customer("Phape", "12345678", "Philipp",
            "Mayer", "7");
    GiroAccount account = new GiroAccount();

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