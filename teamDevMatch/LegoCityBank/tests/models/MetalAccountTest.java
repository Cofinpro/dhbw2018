package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetalAccountTest {

    Customer customer = new Customer("blabla","passwort","Philipp","Lammmes");

    MetalAccount metalAccount = new MetalAccount(customer);

    GoldPrice goldPrice = GoldPrice.getInstance();

    @Test
    void getAccountType() {
        assertEquals("Metall Konto",metalAccount.getAccountType());
    }

    @Test
    void getMonthlyInterest() {
        assertEquals(0.0,metalAccount.getMonthlyInterest());
    }

    @Test
    void getMonthlyFeesPercentage() {
        assertEquals(0.0,metalAccount.getMonthlyFeesPercentage());
    }

    @Test
    void getMonthlyFeesAbsolute() {
        assertEquals(100.0,metalAccount.getMonthlyFeesAbsolute());
    }

    @Test
    void getBalance(){
        double goldAmountInGramHelper = metalAccount.getGoldAmountInGram();
        double actualBalance = goldPrice.calculateWorth(goldAmountInGramHelper);

        assertEquals(actualBalance,metalAccount.getBalance());
    }

    @Test
    void deposit(){
        double beforeDepositInGram = 10;
        double depositAmountInDollar = 20;
        double depositAmountInGram = depositAmountInDollar / goldPrice.getDollarPerGramOfGold();
        double afterDepositInGram = beforeDepositInGram  + depositAmountInGram ;


        metalAccount.deposit(20);
        double help = beforeDepositInGram + metalAccount.getGoldAmountInGram();
        assertEquals(afterDepositInGram,help);
    }
    @Test
    void disburse(){
        double beforeDepositInGram = 10;
        double depositAmountInDollar = 20;
        double depositAmountInGram = depositAmountInDollar / goldPrice.getDollarPerGramOfGold();
        double afterDepositInGram = beforeDepositInGram  - depositAmountInGram ;

        metalAccount.disburse(20);
        double help = beforeDepositInGram + metalAccount.getGoldAmountInGram();
        assertEquals(afterDepositInGram,help);
    }

}