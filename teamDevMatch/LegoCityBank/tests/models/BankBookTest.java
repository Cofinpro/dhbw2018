package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankBookTest {
    Customer customer = new Customer("blabla","passwort","Philipp","Lammmes");

    BankBook bankBook = new BankBook(customer);
    @Test
    void getAccountType() {
        assertEquals(bankBook.getAccountType(),"BankBook");
    }

    @Test
    void getMonthlyInterest() {
        assertEquals(bankBook.getMonthlyInterest(),0.01);
    }

    @Test
    void getMonthlyFeesPercentage() {
        assertEquals(bankBook.getMonthlyFeesPercentage(),0.0);
    }

    @Test
    void getMonthlyFeesAbsolute() {
        assertEquals(bankBook.getMonthlyFeesAbsolute(),0.0);
    }
}