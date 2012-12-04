/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Utilities;

import java.text.DecimalFormat;

/**
 *
 * @author david
 */
public class Pressure {

    private static final double pascalsPerAtmosphere = 101325;
    private final double pascals;

    public Pressure(double pascals) {
        this.pascals = pascals;
    }

    public double inPascals() {
        return this.pascals;
    }

    public double inAtmospheres() {
        return this.pascals / pascalsPerAtmosphere;
    }
    
    public Pressure plus(Pressure other) {
        return new Pressure(pascals + other.pascals);
    }

    public Pressure minus(Pressure other) {
        return new Pressure(pascals - other.pascals);
    }

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
