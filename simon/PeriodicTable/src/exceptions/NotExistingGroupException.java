package exceptions;

public class NotExistingGroupException extends IllegalArgumentException {
    public NotExistingGroupException(int group) {
        super("The group should be between 1 and 18 but is " + group +".");
        if (group > 0 && group < 19) {
            throw new IllegalArgumentException("The group" + group + "actually exists.");
        }
    }
}
