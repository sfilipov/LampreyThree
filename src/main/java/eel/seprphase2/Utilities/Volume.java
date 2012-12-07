/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Utilities;

/**
 *
 * @author drm
 */
public class Volume {
    
    private final double cubicMetres;
    
    public Volume() {
        cubicMetres = 0;
    }
    
    public Volume(double cubicMetres) {
        this.cubicMetres = cubicMetres;
    }
    
    public double inCubicMetres() {
        return this.cubicMetres;
    }

    public Volume plus(Volume other) {
        return new Volume(cubicMetres + other.cubicMetres);
    }
    
    public Volume minus(Volume other) {
        return new Volume(cubicMetres - other.cubicMetres);
    }
    
    public Mass massAt(Density density) {
        return new Mass(cubicMetres * density.inKilogramsPerCubicMetre());
    }
    
    @Override
    public String toString() {
        return Format.toThreeDecimalPlaces(cubicMetres) + "cubic metres";
    }
    
    @Override
    public int hashCode() {
        return (int)cubicMetres;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Volume other = (Volume)obj;
        if (Double.doubleToLongBits(this.cubicMetres) != Double.doubleToLongBits(other.cubicMetres)) {
            return false;
        }
        return true;
    }
    
}
