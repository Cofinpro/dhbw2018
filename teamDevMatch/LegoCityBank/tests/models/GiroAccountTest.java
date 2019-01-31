package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GiroAccountTest {

    Customer customer = new Customer("blabla","passwort","Philipp","Lammmes");

   GiroAccount giroAccount = new GiroAccount(customer);
    @Test
    void getAccountType() {
        assertEquals(giroAccount.getAccountType(),"GiroAccount");
    }

    @Test
    void getMonthlyInterest() {
        assertEquals(giroAccount.getMonthlyInterest(),0.0);
    }

    @Test
    void getMonthlyFeesPercentage() {
        assertEquals(giroAccount.getMonthlyFeesPercentage(),0.0);
    }

    @Test
    void getMonthlyFeesAbsolute() {
        assertEquals(giroAccount.getMonthlyFeesAbsolute(),5.0);
    }
}