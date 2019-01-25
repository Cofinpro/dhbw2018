package exceptions;

import java.util.NoSuchElementException;

public class UserNotFoundException extends NoSuchElementException {
    public UserNotFoundException(String userName) {
        super("The user " + userName + " was not found.");
    }
}
