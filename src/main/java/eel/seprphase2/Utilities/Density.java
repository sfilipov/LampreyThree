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
public class Density {
    
    @JsonProperty
    private double kilogramsPerCubicMetre;

    public Density(double kilogramsPerCubicMetre) {
        this.kilogramsPerCubicMetre = kilogramsPerCubicMetre;
    }

    public double inKilogramsPerCubicMetre() {
        return kilogramsPerCubicMetre;
    }
    
    public static final Density ofLiquidWater() {
        return new Density(1000);
    }

    public Density plus(Density other) {
        return new Density(kilogramsPerCubicMetre + other.kilogramsPerCubicMetre);
    }

    public Density minus(Density other) {
        return new Density(kilogramsPerCubicMetre - other.kilogramsPerCubicMetre);
    }

    @Override
    public String toString() {
        return Format.toThreeDecimalPlaces(kilogramsPerCubicMetre) + "kg/m^3";
    }

    @Override
    public int hashCode() {
        return (int)kilogramsPerCubicMetre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Density other = (Density)obj;
        if (Double.doubleToLongBits(this.kilogramsPerCubicMetre) != Double.doubleToLongBits(other.kilogramsPerCubicMetre)) {
            return false;
        }
        return true;
    }
    
}
