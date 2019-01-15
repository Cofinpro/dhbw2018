import org.junit.Assert;
import org.junit.Test;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;


public class PinTest {

    @Test
    public void pin() {
        Pin pin = new Pin();
        Assert.assertEquals(pin.getPinState(), is(PinState.STANDING));
    }

    @Test
    public void knockOut() {
        Pin pin = new Pin();
        pin.knockOut();
        Assert.assertEquals(pin.getPinState(), is(PinState.KNOCKEDOUT));
    }

    @Test
    public void putPinBackUp() {
        Pin pin = new Pin();
        pin.knockOut();
        pin.putPinBackUp();
        Assert.assertEquals(pin.getPinState(), is(PinState.STANDING));
    }
}