package exceptions;

public class NotExistingPeriodException extends IllegalArgumentException {
    public NotExistingPeriodException(int period) {
        super("The period can only be between 1 and 7 but is " + period + ".");
        if (period > 0 && period < 8) {
            throw new IllegalArgumentException("The period " + period + " actually exists.");
        }
    }
}
