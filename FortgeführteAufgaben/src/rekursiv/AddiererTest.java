package rekursiv;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddiererTest {

    @Test
    public void addSimple() {
        double helpTrue = Addierer.addSimple(4.0,12.5);
        assertEquals(helpTrue,16.5,0.001);
    }

    @Test
    public void addKomplex() {
        Double helpTrue = Addierer.addKomplex(12.0,14.6);
        assertEquals(helpTrue,26.6,0.1);
    }

    @Test
    public void addRekursiv() {
        double x = 4.0;
        double y = 0.0;
        double helpTrue = Addierer.addRekursiv(x,y);


        assertEquals(helpTrue,4.0,0.001);

        y = 3.0;

        helpTrue = Addierer.addRekursiv(x,y);
        assertEquals(helpTrue,7.0,0.001);

    }
}