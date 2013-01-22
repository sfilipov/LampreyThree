/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Utilities;

/**
 *
 * @author David
 */
public class Volume {

    private final double cubicMetres;

    /**
     *
     */
    public Volume() {
        cubicMetres = 0;
    }

    /**
     *
     * @param cubicMetres
     */
    public Volume(double cubicMetres) {
        this.cubicMetres = cubicMetres;
    }

    /**
     *
     * @return
     */
    public double inCubicMetres() {
        return this.cubicMetres;
    }

    /**
     *
     * @param other
     *
     * @return
     */
    public Volume plus(Volume other) {
        return new Volume(cubicMetres + other.cubicMetres);
    }

    /**
     *
     * @param other
     *
     * @return
     */
    public Volume minus(Volume other) {
        return new Volume(cubicMetres - other.cubicMetres);
    }

    /**
     *
     * @param density
     *
     * @return
     */
    public Mass massAt(Density density) {
        return new Mass(cubicMetres * density.inKilogramsPerCubicMetre());
    }

    @Override
    public String toString() {
        return Format.toThreeDecimalPlaces(cubicMetres) + "m^3";
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
