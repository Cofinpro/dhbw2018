package Chemical;

import org.junit.Test;

import static org.junit.Assert.*;

public class PeriodicSystemTest {

    PeriodicSystem systemOne = PeriodicSystem.getInstance();
    PeriodicSystem systemTwo = PeriodicSystem.getInstance();

    @Test
    public void getInstanceTest() {
        assertSame(systemOne,systemTwo);
       if (systemOne.equals(systemTwo)){
            assertEquals(1,1);
        }
    }

    @Test
    public void getChemicalElement() {
    }

    @Test
    public void printChemicalElement() {
    }

    @Test
    public void addChemicalElement() {
    }
}