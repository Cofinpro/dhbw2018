package application.helper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IntegerArrayHelperTest {

    private IntegerArrayHelper integerArrayHelper;

    @Before
    public void setUp() throws Exception {
        integerArrayHelper = new IntegerArrayHelper();
    }

    @Test
    public void getIntArray() {
        Integer[] array = integerArrayHelper.getIntArray(1);
        assertEquals(1, array.length);
    }
}