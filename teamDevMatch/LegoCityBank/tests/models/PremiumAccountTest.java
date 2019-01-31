package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PremiumAccountTest {

    PremiumAccount premiumAccount = new PremiumAccount();
    @Test
    void getAccountType() {
        assertEquals(premiumAccount.getAccountType(),"PremiumAccount");
    }

    @Test
    void getMonthlyInterest() {
        assertEquals(premiumAccount.getMonthlyInterest(),0.0);
    }

    @Test
    void getMonthlyFeesPercentage() {
        assertEquals(premiumAccount.getMonthlyFeesPercentage(),0.005);
    }

    @Test
    void getMonthlyFeesAbsolute() {
        assertEquals(premiumAccount.getMonthlyFeesAbsolute(),0.0);
    }
}