package exceptions;

import java.util.NoSuchElementException;

public class ChemicalElementNotFoundException extends NoSuchElementException {
    public ChemicalElementNotFoundException(int period, int group) {
        super("A element at period " + period + " and group " + group + " could not be found. Either it doesn't exist or this program is incomlete.");
    }
}
