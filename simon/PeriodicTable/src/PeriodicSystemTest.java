import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Collection;

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

    @Test
    public void testGetChemicalElement() {
        PeriodicSystem periodicSystem = PeriodicSystem.getInstance();
        Assert.assertEquals(35, periodicSystem.getChemicalElement(4, 17).getAtomicNumber());
    }
}