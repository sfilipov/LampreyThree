package eel.seprphase2.Utilities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.DecimalFormat;

/**
 *
 * @author David
 */
public class Pressure {

    private static final double pascalsPerAtmosphere = 101325;
    @JsonProperty
    private final double pascals;

    /**
     *
     */
    public Pressure() {
        pascals = 0;
    }

    /**
     *
     * @param pascals
     */
    public Pressure(double pascals) {
        this.pascals = pascals;
    }

    /**
     *
     * @return
     */
    public double inPascals() {
        return this.pascals;
    }

    /**
     *
     * @return
     */
    public double inAtmospheres() {
        return this.pascals / pascalsPerAtmosphere;
    }

    /**
     *
     * @param other
     *
     * @return
     */
    public Pressure plus(Pressure other) {
        return new Pressure(pascals + other.pascals);
    }

    /**
     *
     * @param other
     *
     * @return
     */
    public Pressure minus(Pressure other) {
        return new Pressure(pascals - other.pascals);
    }

    /**
     *
     * @param other
     *
     * @return
     */
    public boolean greaterThan(Pressure other) {
        return pascals > other.pascals;
    }

    @Override
    public String toString() {
        return Format.toThreeDecimalPlaces(inAtmospheres()) + " atm";
    }

    @Override
    public int hashCode() {
        return (int)this.pascals;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pressure other = (Pressure)obj;
        if (this.pascals != other.pascals) {
            return false;
        }
        return true;
    }
}
