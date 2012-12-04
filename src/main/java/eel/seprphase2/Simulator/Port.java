/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import eel.seprphase2.Utilities.*;
import static eel.seprphase2.Utilities.Units.*;

/**
 *
 * @author david
 */
public class Port {

    public Density density = Density.ofLiquidWater();
    public Pressure pressure = pascals(101325);
    public Temperature temperature = kelvin(300);
    public Mass mass = kilograms(0);
}
