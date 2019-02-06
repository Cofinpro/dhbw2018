package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoldPriceTest {
    GoldPrice goldPrice = GoldPrice.getInstance();
    private static final double GRAMSPEROUNCE = 31.1034768;
    @Test
    void getInstance() {
        assertNotEquals(null,goldPrice);
    }

    @Test
    void getDollarPerGramOfGold() {
        double currentDollarPerGramOfGold = 1249.887;
        assertEquals(currentDollarPerGramOfGold/GRAMSPEROUNCE,goldPrice.getDollarPerGramOfGold());
    }

    @Test
    void calculateWorthTest() {
        double goldAmountInGramHelper = 5;
        double correctCalculatedWorth =  200.92;

        double resulOfEquation = goldPrice.calculateWorth(goldAmountInGramHelper);
        assertEquals(correctCalculatedWorth,resulOfEquation);
    }
}