/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Utilities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 *
 * @author drm
 */
@JsonTypeName(value = "Energy")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
public class Energy {

    @JsonProperty
    private double joules;

    /**
     *
     */
    public Energy() {
        joules = 0;
    }

    /**
     *
     * @param joules
     */
    public Energy(double joules) {
        this.joules = joules;
    }

    /**
     *
     * @return
     */
    public double inJoules() {
        return joules;
    }

    public double inKJoules() {
        return joules / 1000;
    }

    /**
     *
     * @param other
     *
     * @return
     */
    public Energy plus(Energy other) {
        return new Energy(joules + other.joules);
    }

    /**
     *
     * @param other
     *
     * @return
     */
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
