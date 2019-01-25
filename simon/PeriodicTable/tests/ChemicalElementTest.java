import models.ChemicalElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;

public class ChemicalElementTest {

    private ChemicalElement hydrogen;
    private ChemicalElement bromine;
    private ChemicalElement lithium;
    private ChemicalElement silicone;

    @Before
    public void setUp()  {
        hydrogen = new ChemicalElement(1, "Hydrogen", "H", 1, 1);
        bromine = new ChemicalElement(35, "Bromine", "Br", 4, 17);
        lithium = new ChemicalElement(3, "Lithium", "Li", 2, 1);
        silicone = new ChemicalElement(14, "Silicone", "Si", 3, 14);
    }

    @Test
    public void isFluid() {
        Assert.assertThat(bromine.isFluid(), is(true));
        Assert.assertThat(hydrogen.isFluid(), is(false));
        Assert.assertThat(lithium.isFluid(), is(false));
        Assert.assertThat(silicone.isFluid(), is(false));
    }

    @Test
    public void isAlkali() {
        Assert.assertThat(lithium.isAlkali(), is(true));
        Assert.assertThat(hydrogen.isAlkali(), is(false));
        Assert.assertThat(bromine.isAlkali(), is(false));
        Assert.assertThat(silicone.isAlkali(), is(false));
    }

    @Test
    public void isMetalloid() {
        Assert.assertThat(silicone.isMetalloid(), is(true));
        Assert.assertThat(hydrogen.isMetalloid(), is(false));
        Assert.assertThat(bromine.isMetalloid(), is(false));
        Assert.assertThat(lithium.isMetalloid(), is(false));
    }

    @Test
    public void compareTo() {
        Assert.assertTrue(silicone.compareTo(hydrogen) > 0);
        Assert.assertTrue(hydrogen.compareTo(silicone) < 0);
        Assert.assertTrue(hydrogen.compareTo(hydrogen) == 0);
    }
}