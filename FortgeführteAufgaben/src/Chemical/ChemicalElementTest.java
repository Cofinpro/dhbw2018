package Chemical;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChemicalElementTest {
    ChemicalElement magnesium = new ChemicalElement(12,"Mg","Magnesium",3,2);
    ChemicalElement oxygen = new ChemicalElement(8,"O","Oxygen",2,16);
    ChemicalElement brom = new ChemicalElement(35,"Br","Brom",4,17);

    @Test
    public void isFluid() {
        assertEquals(magnesium.isFluid(),false);
        assertEquals(oxygen.isFluid(),false);
        assertEquals(brom.isFluid(),true);
    }

    @Test
    public void isGas() {
        assertEquals(magnesium.isGas(),false);
        assertEquals(oxygen.isGas(),true);
        assertEquals(brom.isGas(),false);
    }

    @Test
    public void isEarthAkali() {
        assertEquals(magnesium.isEarthAkali(),true);
        assertEquals(oxygen.isEarthAkali(),false);
        assertEquals(brom.isEarthAkali(),false);
    }

    @Test
    public void compareTo() {
        assertEquals(magnesium.compareTo(magnesium),0);
        assertEquals(magnesium.compareTo(oxygen),1);
        assertEquals(magnesium.compareTo(brom),-1);
        assertEquals(magnesium.compareTo(null),1);

    }
}