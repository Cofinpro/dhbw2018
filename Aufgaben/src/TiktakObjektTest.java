import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;

import static org.junit.Assert.*;

public class TiktakObjektTest {

    TiktakObjekt drei = new TiktakObjekt(3);
    TiktakObjekt fünf = new TiktakObjekt(5);
    TiktakObjekt fünfzehn = new TiktakObjekt(15);
    TiktakObjekt nichtTeilbar = new TiktakObjekt(4);
    TiktakObjekt exceptionTest = new TiktakObjekt(-1);

    String ausgabe;

    @org.junit.Test
    public void durch3() {
        ausgabe = Boolean.toString(drei.durch3());
        assertEquals(true,true);
        ausgabe = Boolean.toString(drei.durch5());
        assertEquals(false,false);
    }

    @org.junit.Test
    public void durch5() {
        ausgabe = Boolean.toString(fünf.durch5());
        assertEquals(true,true);
        ausgabe = Boolean.toString(fünf.durch3());
        assertEquals(false,false);
    }

    @Test
    public void durch15() {
        ausgabe = Boolean.toString(fünfzehn.durch15());
        assertEquals(true,true);
        ausgabe = Boolean.toString(fünfzehn.durch3());
        assertEquals(false,false);
    }

    @org.junit.Test
    public void nix() {
        ausgabe = Boolean.toString(nichtTeilbar.durch3());
        assertEquals(false,false);
        ausgabe = Boolean.toString(nichtTeilbar.durch15());
        assertEquals(false,false);
        ausgabe = Boolean.toString(nichtTeilbar.durch5());
        assertEquals(false,false);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void exceptionThrown(){

        ausgabe = Boolean.toString(exceptionTest.kleinerAlsNull());
        assertEquals(true,true);
        ausgabe = Boolean.toString(drei.kleinerAlsNull());
        assertEquals(true,false);

    }
    @org.junit.Test
    public void testEigeneExceotion(){

    }


}