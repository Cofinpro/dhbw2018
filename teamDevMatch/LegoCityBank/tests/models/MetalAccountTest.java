package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetalAccountTest {

    MetalAccount metalAccount = new MetalAccount();

    @Test
    void getAccountType() {
        assertEquals(metalAccount.getAccountType(),"MetalAccount");
    }

    @Test
    void getMonthlyInterest() {
        assertEquals(metalAccount.getMonthlyInterest(),0.0);
    }

    @Test
    void getMonthlyFeesPercentage() {
        assertEquals(metalAccount.getMonthlyFeesPercentage(),0.0);
    }

    @Test
    void getMonthlyFeesAbsolute() {
        assertEquals(metalAccount.getMonthlyFeesAbsolute(),100.0);
    }

}