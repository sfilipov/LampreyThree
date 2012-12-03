/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import eel.seprphase2.Utilities.Density;
import java.lang.Math;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Velocity;
import static eel.seprphase2.Utilities.Units.*;

/**
 *
 * @author drm
 */
public class Bernoulli {

    public static Velocity velocity(Pressure pressure, Density density) {
        return metresPerSecond(Math.sqrt(2 * pressure.inPascals() * density.inKilogramsPerCubicMetre()));
    }

    public static Density density(Pressure pressure, Velocity velocity) {
        return kilogramsPerCubicMetre((2 * pressure.inPascals()) / Math.pow(velocity.inMetresPerSecond(), 2));
    }
    
    public static Pressure pressure(Density density, Velocity velocity) {
        return pascals(0.5 * density.inKilogramsPerCubicMetre() * Math.pow(velocity.inMetresPerSecond(), 2));
    }
}
