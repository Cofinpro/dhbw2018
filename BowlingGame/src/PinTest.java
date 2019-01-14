import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;


public class PinTest {

    @Test
    public void pin() {
        Pin pin = new Pin();
        Assert.assertThat(pin.getPinState(), is(PinState.STANDING));
    }

    @Test
    public void knockOut() {
        Pin pin = new Pin();
        pin.knockOut();
        Assert.assertThat(pin.getPinState(), is(PinState.KNOCKEDOUT));
    }

    @Test
    public void putPinBackUp() {
        Pin pin = new Pin();
        pin.knockOut();
        pin.putPinBackUp();
        Assert.assertThat(pin.getPinState(), is(PinState.STANDING));
    }
}