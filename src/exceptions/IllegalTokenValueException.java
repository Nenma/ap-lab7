package exceptions;

public class IllegalTokenValueException extends Exception {

    public IllegalTokenValueException() {
        super("Illegal value provided to token!");
    }
}
