/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Utilities;

/**
 *
 * @author drm
 */
public class Units {

    /**
     *
     * @param percentagePoints
     *
     * @return
     */
    public static Percentage percent(int percentagePoints) {
        return new Percentage(percentagePoints);
    }

    /**
     *
     * @param pascals
     *
     * @return
     */
    public static Pressure pascals(double pascals) {
        return new Pressure(pascals);
    }

    /**
     *
     * @param degrees
     *
     * @return
     */
    public static Temperature kelvin(double degrees) {
        return new Temperature(degrees);
    }

    /**
     *
     * @param kilograms
     *
     * @return
     */
    public static Mass kilograms(double kilograms) {
        return new Mass(kilograms);
    }

    /**
     *
     * @param moles
     *
     * @return
     */
    public static Mass molesOfWater(double moles) {
        return Mass.fromMolesOfWater(moles);
    }

    /**
     *
     * @param cubicMetres
     *
     * @return
     */
    public static Volume cubicMetres(double cubicMetres) {
        return new Volume(cubicMetres);
    }

    /**
     *
     * @param joules
     *
     * @return
     */
    public static Energy joules(double joules) {
        return new Energy(joules);
    }

    /**
     *
     * @param kilogramsPerCubicMetre
     *
     * @return
     */
    public static Density kilogramsPerCubicMetre(double kilogramsPerCubicMetre) {
        return new Density(kilogramsPerCubicMetre);
    }

    /**
     *
     * @param metresPerSecond
     *
     * @return
     */
    public static Velocity metresPerSecond(double metresPerSecond) {
        return new Velocity(metresPerSecond);
    }
}
