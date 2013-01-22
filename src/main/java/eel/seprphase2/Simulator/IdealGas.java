package eel.seprphase2.Simulator;

import static eel.seprphase2.Simulator.PhysicalConstants.*;
import eel.seprphase2.Utilities.*;
import static eel.seprphase2.Utilities.Units.*;

/**
 * Ideal Gas Equation
 * 
 * Rearrangements of the Ideal Gas Equation to find any one variable
 * given the others.
 * 
 * @author David
 */
public class IdealGas {

    /**
     *
     * @param volume
     * @param mass
     * @param temperature
     *
     * @return
     */
    public static Pressure pressure(Volume volume, Mass mass, Temperature temperature) {
        return pascals((mass.inMolesOfWater() * gasConstant * temperature.inKelvin()) / volume.inCubicMetres());
    }

    /**
     *
     * @param volume
     * @param mass
     * @param pressure
     *
     * @return
     */
    public static Temperature temperature(Volume volume, Mass mass, Pressure pressure) {
        return kelvin((pressure.inPascals() * volume.inCubicMetres()) / (mass.inKilograms() * gasConstant));
    }

    /**
     *
     * @param pressure
     * @param mass
     * @param temperature
     *
     * @return
     */
    public static Volume volume(Pressure pressure, Mass mass, Temperature temperature) {
        return cubicMetres(mass.inMolesOfWater() * gasConstant * temperature.inKelvin());
    }

    /**
     *
     * @param pressure
     * @param volume
     * @param temperature
     *
     * @return
     */
    public static Mass mass(Pressure pressure, Volume volume, Temperature temperature) {
        return molesOfWater((pressure.inPascals() * volume.inCubicMetres()) / (gasConstant * temperature.inKelvin()));
    }
}
