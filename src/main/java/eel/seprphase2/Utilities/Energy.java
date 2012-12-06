/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Utilities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author drm
 */

public class Energy {
    @JsonProperty
    private double joules;

    public Energy(double joules) {
        this.joules = joules;
    }

    public double inJoules() {
        return joules;
    }

    public Energy plus(Energy other) {
        return new Energy(joules + other.joules);
    }

    public Energy minus(Energy other) {
        return new Energy(joules - other.joules);
    }

    @Override
    public String toString() {
        return Format.toThreeDecimalPlaces(joules) + " J";
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
