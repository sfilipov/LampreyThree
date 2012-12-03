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

    public static Percentage percent(int percentagePoints) {
        return new Percentage(percentagePoints);
    }
    
    public static Pressure pascals(double pascals) {
        return new Pressure(pascals);
    }
    
    public static Temperature kelvin(double degrees) {
        return new Temperature(degrees);
    }
    
    public static Mass kilograms(double kilograms) {
        return new Mass(kilograms);
    }
    
    public static Mass molesOfWater(double moles) {
        return Mass.fromMolesOfWater(moles);
    }
    
    public static Volume cubicMetres(double cubicMetres) {
        return new Volume(cubicMetres);
    }
    
    public static Energy joules(double joules) {
        return new Energy(joules);
    }
    
    public static Density kilogramsPerCubicMetre(double kilogramsPerCubicMetre) {
        return new Density(kilogramsPerCubicMetre);
    }
    
    public static Velocity metresPerSecond(double metresPerSecond) {
        return new Velocity(metresPerSecond);
    }
    
}
