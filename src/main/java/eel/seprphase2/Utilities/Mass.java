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
@JsonTypeName(value="Mass")
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, property="@class")
public class Mass {
    
    @JsonProperty
    private final double kilograms;
    
    public static Mass fromMolesOfWater(double moles) {
        return new Mass(moles * 18 / 1000);
    }
    
    public Mass() {
        kilograms = 0;
    }
    
    public Mass(double kilograms) {
        this.kilograms = kilograms;
    }
    
    public double inKilograms() {
        return kilograms;
    }
    
    public double inMolesOfWater() {
        return kilograms * 1000 / 18;
    }
    
    public Mass plus(Mass other) {
        return new Mass(kilograms + other.kilograms);
    }
    
    public Mass minus(Mass other) {
        return new Mass(kilograms - other.kilograms);
    }
    
    public Density densityAt(Volume volume) {
        return new Density(kilograms / volume.inCubicMetres());
    }
    
    public Volume volumeAt(Density density) {
        return new Volume(kilograms / density.inKilogramsPerCubicMetre());
    }
    
    @Override
    public String toString() {
        return Format.toThreeDecimalPlaces(kilograms) + " kg";
    }
    
    @Override
    public int hashCode() {
        return (int)kilograms;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mass other = (Mass)obj;
        if (Double.doubleToLongBits(this.kilograms) != Double.doubleToLongBits(other.kilograms)) {
            return false;
        }
        return true;
    }
    
}
