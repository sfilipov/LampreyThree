/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Utilities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.text.DecimalFormat;

/**
 *
 * @author david
 */
@JsonTypeName(value="Temperature")
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, property="@class")
public class Temperature {

    static private final double kelvinOffset = 273.15;
    @JsonProperty
    private final double degreesKelvin;

    public Temperature() {
        degreesKelvin = 0;
    }
    
    public Temperature(double degreesKelvin) {
        this.degreesKelvin = degreesKelvin;
    }
    
    public double inCelsius() {
        return this.degreesKelvin - kelvinOffset;
    }

    public double inKelvin() {
        return this.degreesKelvin;
    }
    
    @Override
    public String toString() {
        return Format.toThreeDecimalPlaces(inCelsius()) + " degrees C";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Temperature other = (Temperature)obj;
        if (this.degreesKelvin != other.degreesKelvin) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (int)this.degreesKelvin;
    }
}
