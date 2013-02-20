package eel.seprphase2.Utilities;

import java.io.Serializable;

/**
 *
 * @author David
 */
public class Velocity implements Serializable  {

    private double metresPerSecond;

    /**
     *
     */
    public Velocity() {
        metresPerSecond = 0;
    }

    /**
     *
     * @param metresPerSecond
     */
    public Velocity(double metresPerSecond) {
        this.metresPerSecond = metresPerSecond;
    }

    /**
     *
     * @return
     */
    public double inMetresPerSecond() {
        return metresPerSecond;
    }

    /**
     *
     * @param other
     *
     * @return
     */
    public Velocity plus(Velocity other) {
        return new Velocity(metresPerSecond + other.metresPerSecond);
    }

    /**
     *
     * @param other
     *
     * @return
     */
    public Velocity minus(Velocity other) {
        return new Velocity(metresPerSecond - other.metresPerSecond);
    }

    @Override
    public String toString() {
        return Format.toThreeDecimalPlaces(metresPerSecond) + " m/s";
    }

    @Override
    public int hashCode() {
        return (int)metresPerSecond;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Velocity other = (Velocity)obj;
        if (Double.doubleToLongBits(this.metresPerSecond) != Double.doubleToLongBits(other.metresPerSecond)) {
            return false;
        }
        return true;
    }
}
