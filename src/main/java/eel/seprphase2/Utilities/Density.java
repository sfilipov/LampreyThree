package eel.seprphase2.Utilities;

import java.io.Serializable;


/**
 * Encapsulate a value representing a physical Density
 *
 * @author David
 */
public class Density implements Serializable {

    protected double kilogramsPerCubicMetre;

    /**
     * Default constructor - assumes water
     */
    public Density() {
        kilogramsPerCubicMetre = 1000;
    }

    /**
     *
     * @param kilogramsPerCubicMetre
     */
    public Density(double kilogramsPerCubicMetre) {
        this.kilogramsPerCubicMetre = kilogramsPerCubicMetre;
    }

    /**
     *
     * @return
     */
    public double inKilogramsPerCubicMetre() {
        return kilogramsPerCubicMetre;
    }

    /**
     * Helper method for the density of liquid water
     *
     * @return
     */
    public static Density ofLiquidWater() {
        return new Density(1000);
    }

    /**
     * Subtract one density from another
     *
     *
     * @param other the second operand of the subtraction
     *
     * @return the result of the subtraction
     */
    public Density minus(Density other) {
        return new Density(kilogramsPerCubicMetre - other.kilogramsPerCubicMetre);
    }

    /**
     * Add one Density to another
     *
     * @param other the second operand of the addition
     *
     * @return the result of the addition
     */
    public Density plus(Density other) {
        return new Density(kilogramsPerCubicMetre + other.kilogramsPerCubicMetre);
    }

    /**
     * Printable representation of a density, rounded to three decimal places, with units.
     *
     * @return the density as a String
     */
    @Override
    public String toString() {
        return Format.toThreeDecimalPlaces(kilogramsPerCubicMetre) + "kg/m^3";
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
        if (Double.doubleToLongBits(this.kilogramsPerCubicMetre) != Double
                .doubleToLongBits(other.kilogramsPerCubicMetre)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (int)kilogramsPerCubicMetre;
    }
}
