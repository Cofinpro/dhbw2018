package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetalAccountTest {

    Customer customer = new Customer("blabla","passwort","Philipp","Lammmes");

    MetalAccount metalAccount = new MetalAccount(customer);

    GoldPrice goldPrice = GoldPrice.getInstance();

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

    @Test
    void getBalance(){
        double goldAmountInGramHelper = metalAccount.getGoldAmountInGram();
        double actualBalance = goldPrice.calculateWorth(goldAmountInGramHelper);

        assertEquals(metalAccount.getBalance(),actualBalance);
    }

    @Test
    void deposit(){
        double beforeDepositInGram = 10;
        double depositAmountInDollar = 20;
        double depositAmountInGram = depositAmountInDollar / goldPrice.getDollarPerGramOfGold();
        double afterDepositInGram = beforeDepositInGram  + depositAmountInGram ;


        metalAccount.deposit(20);
        double help = beforeDepositInGram + metalAccount.getGoldAmountInGram();
        assertEquals(help,afterDepositInGram);
    }
    @Test
    void disburse(){
        double beforeDepositInGram = 10;
        double depositAmountInDollar = 20;
        double depositAmountInGram = depositAmountInDollar / goldPrice.getDollarPerGramOfGold();
        double afterDepositInGram = beforeDepositInGram  - depositAmountInGram ;

        metalAccount.disburse(20);
        double help = beforeDepositInGram + metalAccount.getGoldAmountInGram();
        assertEquals(help,afterDepositInGram);
    }

}