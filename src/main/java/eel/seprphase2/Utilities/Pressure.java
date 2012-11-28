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
    private int pascals;
    
    public Pressure(int pascals) {
        this.pascals = pascals;
    }
    
    public int pascals() {
        return this.pascals;
    }
    
    public double atmospheres() {
        return this.pascals / pascalsPerAtmosphere;
    }

    @Override
    public String toString() {
        DecimalFormat form = new DecimalFormat("#.###");
        return form.format(atmospheres()) + "atm";
    }
    
    @Override
    public int hashCode() {
        return this.pascals;
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
