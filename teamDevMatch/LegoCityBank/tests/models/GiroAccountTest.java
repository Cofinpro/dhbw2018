package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GiroAccountTest {

   GiroAccount giroAccount = new GiroAccount();
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