import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChemicalElementTest {

    //arrange
    ChemicalElement H = new ChemicalElement(1,1,1, "H", "Hydrogen");
    ChemicalElement Mg = new ChemicalElement(12,3,2,"Mg","Magnesium");
    ChemicalElement Hg = new ChemicalElement(80,6,12,"Hg","Mercury");
    ChemicalElement K = new ChemicalElement(19,4,1,"K","Potassium");


    @org.junit.jupiter.api.Test
    void isAlkali() {
        //act + assert
        assertFalse(H.isAlkali(H.atomicNumber));
        assertFalse(Mg.isAlkali(Mg.atomicNumber));
        assertFalse(Hg.isAlkali(Hg.atomicNumber));
        assertTrue(K.isAlkali(K.atomicNumber));
    }

    @org.junit.jupiter.api.Test
    void isFluid() {
        assertFalse(H.isFluid(H.period, H.group));
        assertFalse(Mg.isFluid(Mg.period, Mg.group));
        assertTrue(Hg.isFluid(Hg.period, Hg.group));
        assertFalse(K.isFluid(K.period, K.group));
    }

    @org.junit.jupiter.api.Test
    void isGas() {
        assertTrue(H.isGas(H.atomicNumber));
        assertFalse(Mg.isGas(Mg.atomicNumber));
        assertFalse(Hg.isGas(Hg.atomicNumber));
        assertFalse(K.isGas(K.atomicNumber));
    }
    @Test
    void compareTo() {
        assertEquals(-1, H.compareTo(Mg), "Compared AtomicNumber wrong");
        assertEquals(1, Hg.compareTo(Mg), "Compared AtomicNumber wrong");
        assertEquals(0, K.compareTo(K), "Compared AtomicNumber wrong");
    }
}
