package BowlingSpiel;

/**
 * represents a pin
 * @author Team DevMatch - Cofinpro AG
 */
public class Pin {

    private PinState testpin;
    /**
     * creates a standing pin
     */
    public Pin(){
        testpin = PinState.STANDING;
    }

    /**
     * changes the state of the pin from STANDING to KNOCKEDOUT
     */
    public void knockOut(){
        testpin = PinState.KNOCKEDOUT;
    }

    /**
     * changes the state of the pin from KNOCKEDOUT to STANDING
     */
    public void putPinBackUp(){
        testpin = PinState.STANDING;

    }

    public PinState getPinState(){
        return testpin;
    }
}