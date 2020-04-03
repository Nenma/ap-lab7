package model;

import exceptions.IllegalTokenValueException;

/**
 * Class simulating a playable piece on the Board that can only
 * contain a number between 1 and NUMBER_LIMIT or 0 (wildcard number)
 */
public class Token {

    public static final int NUMBER_LIMIT = 50; // This is the 'm' in the problem
    private int value;

    public Token() {
        value = 0; // Default constructor gives the 'blank' value
    }

    public Token(int value) throws IllegalTokenValueException {
        setValue(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) throws IllegalTokenValueException {
        if (value < 0) {
            throw new IllegalTokenValueException();
        } else {
            this.value = value;
        }
    }

    @Override
    public String toString() {
        return "[" + value + ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        return value == token.value;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + value;
        return result;
    }
}
