package eel.seprphase2.Utilities;

import java.io.Serializable;

/**
 * Encapsulates a physical quantity representing an Energy
 *
 * @author David
 */
public class Energy implements Serializable {

    private double joules;

    public Energy() {
        joules = 0;
    }

    public Energy(double joules) {
        this.joules = joules;
    }

    public double inJoules() {
        return joules;
    }

    public double inKJoules() {
        return joules / 1000;
    }

    /**
     * Add one Energy to another
     *
     * @param other the second operand of the addition
     *
     * @return the result of the addition
     */
    public Energy plus(Energy other) {
        return new Energy(joules + other.joules);
    }

    /**
     * Subtract one Energy from another
     *
     * @param other the second operand of the subtraction
     *
     * @return the result of the subtraction
     */
    public Energy minus(Energy other) {
        return new Energy(joules - other.joules);
    }

    /**
     * The Energy as a string, formatted to 3 decimal places with units.
     *
     * As energy values can be very large, this function makes use of the kilo-, mega-, and giga- prefixes
     *
     * @return string representation of the energy
     */
    @Override
    public String toString() {

        if (joules >= 10000000000.0) {
            return Format.toThreeDecimalPlaces(joules / 1000000000.0) + " GJ";
        } else if (joules >= 1000000.0) {
            return Format.toThreeDecimalPlaces(joules / 1000000.0) + " MJ";
        } else if (joules >= 1000) {
            return Format.toThreeDecimalPlaces(joules / 1000) + " kJ";
        } else {
            return Format.toThreeDecimalPlaces(joules) + " J";
        }
    }

    @Override
    public int hashCode() {
        return (int)joules;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Energy other = (Energy)obj;
        if (Double.doubleToLongBits(this.joules) != Double.doubleToLongBits(other.joules)) {
            return false;
        }
        return true;
    }
}
