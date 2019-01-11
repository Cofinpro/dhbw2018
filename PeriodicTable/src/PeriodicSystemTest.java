import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PeriodicSystemTest {

    /**
     * tests whether the singleton pattern works
     */
    @Test
    public void getInstance() {
        PeriodicSystem one = PeriodicSystem.getInstance();
        PeriodicSystem two = PeriodicSystem.getInstance();
        Assert.assertTrue(one == two);
    }
}