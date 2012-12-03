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
public class Temperature {

    static private final double kelvinOffset = 273.15;
    
    private double degreesKelvin;

    public Temperature(double degreesKelvin) {
        this.degreesKelvin = degreesKelvin;
    }
    
    public double degreesCelsius() {
        return this.degreesKelvin - kelvinOffset;
    }

    public double degreesKelvin() {
        return this.degreesKelvin;
    }
    
    @Override
    public String toString() {
        DecimalFormat form = new DecimalFormat("#.###");
        return form.format(degreesCelsius()) + " degrees C";
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
