package eel.seprphase2.Simulator;

import eel.seprphase2.Utilities.Density;
import eel.seprphase2.Utilities.Pressure;
import static eel.seprphase2.Utilities.Units.*;
import eel.seprphase2.Utilities.Velocity;

/**
 * Bernoulli's Law
 * 
 * Rearrangements of Bernoulli's Law for fluid flow
 * to find the value of any variable from any of the others.
 * 
 * @author David
 */
public class Bernoulli {

    /**
     *
     * @param pressure
     * @param density
     *
     * @return
     */
    public static Velocity velocity(Pressure pressure, Density density) {
        return metresPerSecond(Math.sqrt(2 * pressure.inPascals() * density.inKilogramsPerCubicMetre()));
    }

    /**
     *
     * @param pressure
     * @param velocity
     *
     * @return
     */
    public static Density density(Pressure pressure, Velocity velocity) {
        return kilogramsPerCubicMetre((2 * pressure.inPascals()) / Math.pow(velocity.inMetresPerSecond(), 2));
    }

    /**
     *
     * @param density
     * @param velocity
     *
     * @return
     */
    public static Pressure pressure(Density density, Velocity velocity) {
        return pascals(0.5 * density.inKilogramsPerCubicMetre() * Math.pow(velocity.inMetresPerSecond(), 2));
    }
}
