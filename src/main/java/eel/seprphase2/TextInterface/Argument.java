package eel.seprphase2.TextInterface;

import eel.seprphase2.Utilities.Percentage;

/**
 * Class encapsulating a string argument to a user command
 *
 * @author David
 */
public class Argument {

    private final String value;

    public Argument(String value) {
        this.value = value;
    }

    /**
     * Attempt to convert the argument to an integer, and throw an exception with a useful error message if this is
     * impossible
     *
     * @return the argument as an integer
     *
     * @throws ArgumentConversionException
     */
    public int asInteger() throws ArgumentConversionException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new ArgumentConversionException("'" + value + "' is not a valid integer");
        }
    }

    /**
     * Attempt to convert the argument to a Percentage, and throw an exception with a useful error message if this is
     * impossible
     *
     * @return the argument as a Percentage
     *
     * @throws ArgumentConversionException
     */
    public Percentage asPercentage() throws ArgumentConversionException {
        try {
            return new Percentage(value);
        } catch (IllegalArgumentException e) {
            throw new ArgumentConversionException("'" + value + "' is not a valid percentage");
        }
    }

    /**
     * Attempts to convert the argument to an integer, ensuring that it is positive, and throws an exception with a
     * useful error message if this is impossible
     *
     * @return the argument as an integer
     *
     * @throws ArgumentConversionException
     */
    public int asPositiveInteger() throws ArgumentConversionException {
        int x = asInteger();
        if (x < 0) {
            throw new ArgumentConversionException("'" + value + "' is not a positive integer");
        }
        return x;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (value.equals(obj)) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Argument other = (Argument)obj;
        if ((this.value == null) ? (other.value != null) : !this.value.equals(other.value)) {
            return false;
        }
        return true;
    }
}
