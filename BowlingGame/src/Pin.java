/**
 * represents a pin
 * @author Team DevMatch - Cofinpro AG
 */
public class Pin {
    PinState pinState = PinState.STANDING;
    /**
     * creates a standing pin
     */
    public Pin(){

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

    /**
     * returns wheather pin is STANDING or KNOCKEDOUT
     * @return current state of pin
     */
    public PinState getPinState(){
            return this.pinState;

    }
}
