/**
 * represents a pin
 * @author Team DevMatch - Cofinpro AG
 */
public class Pin {
    private PinState pinState;
    /**
     * creates a standing pin
     */
    public Pin(){
        pinState = PinState.STANDING;
    }

    /**
     * changes the state of the pin from STANDING to KNOCKEDOUT
     */
    public void knockOut(){
        pinState = PinState.KNOCKEDOUT;
    }

    /**
     * changes the state of the pin from KNOCKEDOUT to STANDING
     */
    public void putPinBackUp(){
        pinState = PinState.STANDING;
    }

    public PinState getPinState() {
        return pinState;
    }
}
