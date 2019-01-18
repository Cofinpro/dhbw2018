package sample;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PolynomialFunctionTest {

    @Test
    public void getY() {
        PolynomialFunction pn = new PolynomialFunction("#miregalwiediefunktionheißt",1,0,0);
        double x = -3;
        double y = pn.getY(x);
        double expectedY = 9;
        Assert.assertEquals(expectedY, y, 0.5);

        pn = new PolynomialFunction("#miregalwiediefunktionheißt",2,1,105);
        x = -3;
        y = pn.getY(x);
        expectedY = 120;
        Assert.assertEquals(expectedY, y, 0.5);
    }
}